package com.tarobu612.youtubesearchapp.model

import com.squareup.moshi.Json

data class YouTubeSearchResponse(
    @Json(name = "kind")
    val kind: String,

    @Json(name = "etag")
    val eTag: String,

    @Json(name = "nextPageToken")
    val nextPageToken: String,

    @Json(name = "prevPageToken")
    val prevPageToken: String,

    @Json(name = "pageInfo")
    val pageInfo: PageInfo,

    @Json(name = "items")
    val items: List<VideoInfo>
) {
    data class VideoInfo(
        @Json(name = "kind")
        val kind: String,

        @Json(name = "etag")
        val eTag: String,

        @Json(name = "id")
        val id: IdInfo,

        @Json(name = "snippet")
        val snippet: SnippetInfo
    )

    data class SnippetInfo(
        @Json(name = "publishedAt")
        val publishedAt: String,

        @Json(name = "channelId")
        val channelId: String,

        @Json(name = "title")
        val title: String,

        @Json(name = "description")
        val description: String,

        @Json(name = "thumbnails")
        val thumbnails: Thumbnail,

        @Json(name = "categoryId")
        val categoryId: String,

        @Json(name = "liveBroadcastContent")
        val liveBroadcastContent: String,

        @Json(name = "publishTime")
        val publishTime: String
    )

    data class Thumbnail(
        @Json(name = "default")
        val default: ThumbnailInfo,

        @Json(name = "medium")
        val medium: ThumbnailInfo,

        @Json(name = "high")
        val high: ThumbnailInfo
    )

    data class ThumbnailInfo(
        @Json(name = "url")
        val url: String,

        @Json(name = "width")
        val width: Int,

        @Json(name = "height")
        val height: Int
    )

    data class PageInfo(
        @Json(name = "totalResults")
        val totalResults: Int,

        @Json(name = "resultsPerPage")
        val resultsPerPage: Int
    )

    data class IdInfo(
        @Json(name = "kind")
        val kind: String,

        @Json(name = "videoId")
        val videoId: String
    )
}