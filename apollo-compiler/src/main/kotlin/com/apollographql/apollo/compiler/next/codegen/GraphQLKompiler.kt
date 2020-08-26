package com.apollographql.apollo.compiler.next.codegen

import com.apollographql.apollo.compiler.OperationIdGenerator
import com.apollographql.apollo.compiler.PackageNameProvider
import com.apollographql.apollo.compiler.ast.CustomTypes
import com.apollographql.apollo.compiler.codegen.kotlin.KotlinCodeGen.patchKotlinNativeOptionalArrayProperties
import com.apollographql.apollo.compiler.ir.CodeGenerationIR
import com.apollographql.apollo.compiler.ir.ScalarType
import com.apollographql.apollo.compiler.ir.TypeDeclaration
import com.apollographql.apollo.compiler.next.ast.buildCodeGenerationAst
import com.apollographql.apollo.compiler.parser.introspection.IntrospectionSchema
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import java.io.File

class GraphQLKompiler(
    private val ir: CodeGenerationIR,
    private val schema: IntrospectionSchema,
    private val customTypes: Map<String, String>,
    private val packageNameProvider: PackageNameProvider,
    private val useSemanticNaming: Boolean,
    private val generateAsInternal: Boolean = false,
    private val operationIdGenerator: OperationIdGenerator,
    private val kotlinMultiPlatformProject: Boolean,
    private val enumAsSealedClassPatternFilters: List<Regex>
) {
  fun write(outputDir: File) {
    val customTypeMap = customTypes.supportedCustomTypes(ir.typesUsed)
    val ast = ir.buildCodeGenerationAst(
        schema = schema,
        customTypeMap = customTypeMap,
        operationIdGenerator = operationIdGenerator,
        useSemanticNaming = useSemanticNaming,
        typesPackageName = packageNameProvider.typesPackageName,
        fragmentsPackage = packageNameProvider.fragmentsPackageName
    )

    customTypeMap
        .typeSpec(generateAsInternal)
        .fileSpec(packageNameProvider.typesPackageName)
        .writeTo(outputDir)

    ast.enumTypes.forEach { enumType ->
      enumType
          .typeSpec(
              generateAsInternal = generateAsInternal,
              enumAsSealedClassPatternFilters = enumAsSealedClassPatternFilters
          )
          .fileSpec(packageNameProvider.typesPackageName)
          .writeTo(outputDir)
    }

    ast.inputTypes.forEach { inputType ->
      inputType
          .typeSpec(generateAsInternal)
          .fileSpec(packageNameProvider.typesPackageName)
          .writeTo(outputDir)
    }

    ast.operationTypes.forEach { operationType ->
      val targetPackage = packageNameProvider.operationPackageName(operationType.filePath)
      operationType
          .typeSpec(
              targetPackage = targetPackage,
              generateAsInternal = generateAsInternal
          )
          .let {
            if (kotlinMultiPlatformProject) {
              it.patchKotlinNativeOptionalArrayProperties()
            } else it
          }
          .fileSpec(targetPackage)
          .writeTo(outputDir)
    }

    ast.fragmentTypes.forEach { fragmentType ->
      fragmentType
          .typeSpec(generateAsInternal)
          .fileSpec(packageNameProvider.fragmentsPackageName)
          .writeTo(outputDir)
    }
  }

  private fun Map<String, String>.supportedCustomTypes(typeDeclarations: List<TypeDeclaration>): CustomTypes {
    val idScalarTypeMap = ScalarType.ID.name to (this[ScalarType.ID.name] ?: String::class.asClassName().toString())
    return CustomTypes(
        typeDeclarations
            .filter { it.kind == TypeDeclaration.KIND_SCALAR_TYPE }
            .associate { it.name to (this[it.name] ?: Any::class.asClassName().canonicalName) }
            .plus(idScalarTypeMap)
    )
  }

  private fun TypeSpec.fileSpec(packageName: String) =
      FileSpec
          .builder(packageName, name!!)
          .addType(this)
          .addComment("AUTO-GENERATED FILE. DO NOT MODIFY.\n\n" +
              "This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.\n" +
              "It should not be modified by hand.\n"
          )
          .build()
}
