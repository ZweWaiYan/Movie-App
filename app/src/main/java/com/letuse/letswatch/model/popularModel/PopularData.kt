package com.letuse.letswatch.model.popularModel

data class PopularData(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)