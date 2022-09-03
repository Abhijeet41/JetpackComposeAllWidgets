package com.abhi41.jetpackcodingwithcat.examples.viewpager.model

data class Nature(
    val title: String,
    val rating: Double,
    val description: String,
    val imgUri: String,
)

val listNatures = listOf(
    Nature(
        "computer",
        3.4,
        "Its a personal computer bla bla bla...........",
        "https://i.picsum.photos/id/1/5616/3744.jpg?hmac=kKHwwU8s46oNettHKwJ24qOlIAsWN9d2TtsXDoCWWsQ"
    ),
    Nature(
        "blue sky",
        4.5,
        "Nature of blue sky forest etc............",
        "https://i.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68"
    ),
    Nature(
        "beach",
        4.6,
        "Its a beach so a lot of people come for having fun ...........",
        "https://i.picsum.photos/id/100/2500/1656.jpg?hmac=gWyN-7ZB32rkAjMhKXQgdHOIBRHyTSgzuOK6U0vXb1w"
    ),
    Nature(
        "Ice Mountain",
        4.9,
        "Ice Mountain Ice Mountain Ice Mountain Ice Mountain Ice Mountain ...........",
        "https://i.picsum.photos/id/1000/5626/3635.jpg?hmac=qWh065Fr_M8Oa3sNsdDL8ngWXv2Jb-EE49ZIn6c0P-g"
    )



)