package com.github.crisacm.animefinder.data.api.model

data class AnimeErrorResponse(
  val status: Int,
  val type: String,
  val messages: Map<String, List<String>>,
  val error: String
)

/*
{
    "status": 400,
    "type": "ValidationException",
    "messages": {
        "limit": [
        "Value 30 is higher than the configured '25' max value."
        ]
    },
    "error": "Invalid or incomplete request. Make sure your request is correct. https://docs.api.jikan.moe/"
}
*/
