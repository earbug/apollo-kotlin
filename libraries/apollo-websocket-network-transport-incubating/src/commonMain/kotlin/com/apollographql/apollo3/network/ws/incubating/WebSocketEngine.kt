package com.apollographql.apollo3.network.ws.incubating

import com.apollographql.apollo3.api.http.HttpHeader
import com.apollographql.apollo3.exception.ApolloException
import okio.Closeable

interface WebSocketEngine: Closeable {
  /**
   * Creates a new [WebSocket].
   *
   * The [WebSocket] is be garbage collected when not used anymore. Call [WebSocket.close] to close the websocket and release resources earlier.
   * You don't need to call [WebSocket.close] if:
   * - [WebSocketListener.onError] has been called
   * - [WebSocketListener.onClosed] has been called
   *
   * @param url: an url starting with one of:
   * - ws://
   * - wss://
   * - http://
   * - https://
   *
   * If the underlying engine requires a ws or wss, http and https are replaced by ws and wss respectively
   */
  fun newWebSocket(
      url: String,
      headers: List<HttpHeader> = emptyList(),
      listener: WebSocketListener
  ): WebSocket
}

interface WebSocketListener {
  /**
   * The HTTP 101 Switching Protocols response has been received and is valid.
   */
  fun onOpen()

  /**
   * A text message has been received.
   */
  fun onMessage(text: String)


  /**
   * A data message has been received.
   */
  fun onMessage(data: ByteArray)

  /**
   * An error happened, no more calls to the listener are made.
   */
  fun onError(cause: ApolloException)

  /**
   * The server sent a close frame, no more calls to the listener are made.
   */
  fun onClosed(code: Int?, reason: String?)
}

interface WebSocket {
  /**
   * Sends a binary message asynchronously.
   *
   * There is no flow control. If the application is sending messages too fast, the connection is closed.
   */
  fun send(data: ByteArray)

  /**
   * Sends a text message asynchronously.
   *
   * There is no flow control. If the application is sending messages too fast, the connection is closed.
   */
  fun send(text: String)

  /**
   * Closes the websocket gracefully and asynchronously.
   *
   * After this call, no more calls to the listener are made
   *
   * Note: there is no API to retrieve the server close code because Apple cancelWithCloseCode calls the
   * URLSession delegate with the same (client) code.
   */
  fun close(code: Int, reason: String)
}

expect fun WebSocketEngine() : WebSocketEngine

internal const val CLOSE_NORMAL = 1000
internal const val CLOSE_GOING_AWAY = 1001
