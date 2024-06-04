package com.example.minichat.http

import android.content.Context
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 *  Url base de la API.
 */
const val BASE_URL = "https://pdm.h130.dev/"

/**
 * La clase RequestInstance es responsable de crear y gestionar las solicitudes HTTP.
 * Utiliza el Request.Builder de OkHttp para construir la solicitud.
 *
 * @property requestBuilder El constructor utilizado para crear la solicitud HTTP.
 * @property method El método HTTP (GET, POST, PUT, PATH, DELETE) para la solicitud.
 *
 * @constructor Inicializa una nueva instancia de RequestInstance con el método GET por defecto.
 *
 * @companionObject
 * @function combineUrl Combina la URL base con la ruta del endpoint.
 * @function createRequestBuilderApi Crea una nueva instancia de RequestInstance con el método y la URL especificados.
 *
 * @function request Devuelve la solicitud construida.
 * @function requestBuilder Devuelve el constructor de la solicitud.
 * @function addHeader Añade un encabezado a la solicitud.
 * @function addAuthToken Añade un encabezado de Autorización con un token Bearer a la solicitud.
 * @function addBody Añade un cuerpo a la solicitud.
 * @function addJsonBody Añade un cuerpo JSON a la solicitud y establece los encabezados apropiados.
 */
class RequestInstance(
  method: RequestMethod?,
  url: String,
  body: String? = null,
  typeBody: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
) {

  /**
   * El constructor utilizado para crear la solicitud HTTP.
   */
  var requestBuilder = Request.Builder()

  /**
   * La URL del endpoint.
   */
  var url: String = ""

  /**
   * El método HTTP (GET, POST, PUT, PATH, DELETE) para la solicitud.
   */
  var method: RequestMethod = RequestMethod.GET

  /**
   * Body de la solicitud.
   */
  var body: RequestBody? = null

  /**
   * Cabecera de la solicitud.
   */
  var headers = mutableMapOf<String, String>()


  companion object {

    /**
     * Combina la URL base con la ruta del endpoint.
     *
     * @param baseUrl La URL base.
     * @param path La ruta del endpoint.
     * @return La URL completa.
     */
    fun combineUrl(baseUrl: String, path: String): String {
      return "${baseUrl.removeSuffix("/")}/${path.removePrefix("/")}"
    }
  }

  /**
   * Inicializa una nueva instancia de RequestInstance con el método GET por defecto.
   *
   * @param method El método HTTP (GET, POST, PUT, PATH, DELETE) para la solicitud.
   * @param url La URL del endpoint.
   * @param body El cuerpo de la solicitud.
   */
  init {
    this.method = method ?: RequestMethod.GET
    this.url(url)
    if (body != null) this.addBody(body, typeBody)
  }

  /**
   * Obtiene la instancia de la solicitud.
   * @return La solicitud construida.
   */
  fun request(): Request {
    requestBuilder
      .url(url)
      .method(method.toString(), body)

    headers.toMap().forEach { (key, value) ->
      requestBuilder.addHeader(key, value)
    }

    return requestBuilder.build()
  }

  /**
   * Obtiene el constructor de la solicitud.
   * @return El constructor de la solicitud.
   */
  fun requestBuilder(): Request.Builder {
    return requestBuilder
  }

  /**
   * Añade la URL del endpoint a la solicitud.
   */
  fun url(url: String): RequestInstance {
    this.url = combineUrl(BASE_URL, url)
    return this
  }

  /**
   * Añade un encabezado a la solicitud.
   *
   * @param key La clave del encabezado.
   * @param value El valor del encabezado.
   * @return La instancia de la solicitud.
   */
  fun addHeader(key: String, value: String): RequestInstance {
    this.headers[key] = value
    return this
  }

  /**
   * Añade un encabezado de Autorización con un token Bearer a la solicitud.
   *
   * @param token El token Bearer.
   * @return La instancia de la solicitud.
   */
  fun addAuthToken(token: String): RequestInstance {
    this.headers["Authorization"] = "Bearer $token"
    return this
  }

  /**
   * Añade un cuerpo a la solicitud.
   *
   * @param body El cuerpo de la solicitud.
   * @return La instancia de la solicitud.
   */
  fun addBody(
    body: String?,
    type: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
  ): RequestInstance {
    this.body = body?.toRequestBody(type)
    return this
  }

  fun addAcceptHeader(value: String): RequestInstance {
    this.addHeader("Accept", value)
    return this
  }

  fun addAcceptHeaderJson(): RequestInstance {
    this.addHeader("Accept", "application/json")
    return this
  }

  /**
   * Añade un cuerpo JSON a la solicitud y establece los encabezados apropiados.
   *
   * @param json El cuerpo JSON de la solicitud.
   * @return La instancia de la solicitud.
   */
  fun addJsonBody(json: String): RequestInstance {
    this.addBody(json, "application/json; charset=utf-8".toMediaTypeOrNull())
    this.addHeader("Content-Type", "application/json")
    return this
  }

  fun execute(): String? {
    val client = OkHttpClient()
    var responseBody: String? = null
    client.newCall(request()).execute().use { response ->
      if (!response.isSuccessful) {
        throw Exception("Unexpected code $response")
      }
      responseBody = response.body?.string()
    }
    return responseBody
  }

  fun executeAsync(context: Context, callback: (okhttp3.Response?) -> Unit) {
    val client = OkHttpClient()
    val request = request()

    client.newCall(request).enqueue(object : okhttp3.Callback {

      var mainHandler = android.os.Handler(context.mainLooper)

      override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
        mainHandler.post {
          //Log.v("ReqIns", e.toString())
          callback(null)
        }
      }

      override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
        mainHandler.post {
          //Log.v("ReqIns", response.toString())
          callback(response)
        }
      }
    })
  }
}