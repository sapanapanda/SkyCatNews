package com.example.catnews.network.response

data class CatStory(
    val id: String,
    val headline: String,
    val heroImage: HeroImage,
    val creationDate: String,
    val modifiedDate: String,
    val contents: List<ContentItem>
)

data class HeroImage(
    val imageUrl: String,
    val accessibilityText: String
)

data class ContentItem(
    val type: String,
    val text: String? = null,
    val url: String? = null,
    val accessibilityText: String? = null
)