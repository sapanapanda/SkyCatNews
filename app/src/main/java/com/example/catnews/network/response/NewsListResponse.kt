package com.example.catnews.network.response

data class NewsListResponse(
    val title: String,
    val data: List<CatNewsItem>
)

data class CatNewsItem(
    val id: String?,
    val type: String?,
    val headline: String?,
    val teaserText: String?,
    val creationDate: String?,
    val modifiedDate: String?,
    val teaserImage: TeaserImage?,
    val url: String?,
    val weblinkUrl: String?,
    val accessibilityText: String?
)

data class TeaserImage(
    val _links: ImageLinks?,
    val accessibilityText: String?
)

data class ImageLinks(
    val url: ImageUrl?
)

data class ImageUrl(
    val href: String?,
    val templated: Boolean?,
    val type: String?
)
