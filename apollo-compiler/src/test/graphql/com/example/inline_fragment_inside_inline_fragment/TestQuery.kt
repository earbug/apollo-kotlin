// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_fragment_inside_inline_fragment

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import kotlin.Array
import kotlin.Boolean
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper.invoke {
    Data(it)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(byteString: ByteString, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = parse(Buffer().write(byteString), scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> = parse(source, DEFAULT)

  @Throws(IOException::class)
  override fun parse(byteString: ByteString): Response<Data> = parse(byteString, DEFAULT)

  override fun composeRequestBody(scalarTypeAdapters: ScalarTypeAdapters): ByteString =
      OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = scalarTypeAdapters
  )

  override fun composeRequestBody(): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = DEFAULT
  )

  override fun composeRequestBody(
    autoPersistQueries: Boolean,
    withQueryDocument: Boolean,
    scalarTypeAdapters: ScalarTypeAdapters
  ): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = autoPersistQueries,
    withQueryDocument = withQueryDocument,
    scalarTypeAdapters = scalarTypeAdapters
  )

  interface SearchSearchResult {
    fun marshaller(): ResponseFieldMarshaller
  }

  interface AsCharacterCharacter {
    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A humanoid creature from the Star Wars universe
   */
  data class AsHuman(
    val __typename: String = "Human",
    /**
     * What this human calls themselves
     */
    val name: String,
    /**
     * The home planet of the human, or null if unknown
     */
    val homePlanet: String?
  ) : AsCharacterCharacter {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@AsHuman.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@AsHuman.name)
      writer.writeString(RESPONSE_FIELDS[2], this@AsHuman.homePlanet)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forString("homePlanet", "homePlanet", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): AsHuman = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        val homePlanet = readString(RESPONSE_FIELDS[2])
        AsHuman(
          __typename = __typename,
          name = name,
          homePlanet = homePlanet
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<AsHuman> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  data class AsDroid(
    val __typename: String = "Droid",
    /**
     * What others call this droid
     */
    val name: String,
    /**
     * This droid's primary function
     */
    val primaryFunction: String?
  ) : AsCharacterCharacter {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@AsDroid.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@AsDroid.name)
      writer.writeString(RESPONSE_FIELDS[2], this@AsDroid.primaryFunction)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forString("primaryFunction", "primaryFunction", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): AsDroid = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        val primaryFunction = readString(RESPONSE_FIELDS[2])
        AsDroid(
          __typename = __typename,
          name = name,
          primaryFunction = primaryFunction
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<AsDroid> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class AsCharacter(
    val __typename: String = "Character",
    /**
     * The name of the character
     */
    val name: String,
    val asHuman: AsHuman?,
    val asDroid: AsDroid?
  ) : SearchSearchResult {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@AsCharacter.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@AsCharacter.name)
      writer.writeFragment(this@AsCharacter.asHuman?.marshaller())
      writer.writeFragment(this@AsCharacter.asDroid?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forFragment("__typename", "__typename", listOf(
            ResponseField.Condition.typeCondition(arrayOf("Human"))
          )),
          ResponseField.forFragment("__typename", "__typename", listOf(
            ResponseField.Condition.typeCondition(arrayOf("Droid"))
          ))
          )

      operator fun invoke(reader: ResponseReader): AsCharacter = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        val asHuman = readFragment<AsHuman>(RESPONSE_FIELDS[2]) { reader ->
          AsHuman(reader)
        }
        val asDroid = readFragment<AsDroid>(RESPONSE_FIELDS[3]) { reader ->
          AsDroid(reader)
        }
        AsCharacter(
          __typename = __typename,
          name = name,
          asHuman = asHuman,
          asDroid = asDroid
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<AsCharacter> = ResponseFieldMapper { invoke(it) }
    }
  }

  data class Search(
    val __typename: String = "SearchResult",
    val asCharacter: AsCharacter?
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Search.__typename)
      writer.writeFragment(this@Search.asCharacter?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forFragment("__typename", "__typename", listOf(
            ResponseField.Condition.typeCondition(arrayOf("Human", "Droid"))
          ))
          )

      operator fun invoke(reader: ResponseReader): Search = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val asCharacter = readFragment<AsCharacter>(RESPONSE_FIELDS[1]) { reader ->
          AsCharacter(reader)
        }
        Search(
          __typename = __typename,
          asCharacter = asCharacter
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Search> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  data class Data(
    val search: List<Search?>?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeList(RESPONSE_FIELDS[0], this@Data.search) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())}
      }
    }

    fun searchFilterNotNull(): List<Search>? = search?.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forList("search", "search", mapOf<String, Any>(
            "text" to "bla-bla"), true, null)
          )

      operator fun invoke(reader: ResponseReader): Data = reader.run {
        val search = readList<Search>(RESPONSE_FIELDS[0]) { reader ->
          reader.readObject<Search> { reader ->
            Search(reader)
          }
        }
        Data(
          search = search
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Data> = ResponseFieldMapper { invoke(it) }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "4f32ea4bdd2a95a29bde61273602c22c698cd333f1701001d1a339fb276c6438"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  search(text: "bla-bla") {
          |    __typename
          |    ... on Character {
          |      __typename
          |      name
          |      ... on Human {
          |        homePlanet
          |      }
          |      ... on Droid {
          |        primaryFunction
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String = "TestQuery"
    }
  }
}
