package com.letuse.letswatch.model.upcomingModel

data class UpComingData(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)