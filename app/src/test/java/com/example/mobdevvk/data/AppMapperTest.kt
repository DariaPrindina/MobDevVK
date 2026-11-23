package com.example.mobdevvk.data

import com.example.mobdevvk.domain.Category
import com.example.mobdevvk.testutil.appDetailsDto
import com.example.mobdevvk.testutil.appItemDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppMapperTest {

    private val mapper = AppMapper()

    @Test
    fun itemToDomain_mapsAllFields() {
        val dto = appItemDto(
            id = "id-1",
            name = "QuickNote",
            description = "Notes app",
            category = "Производительность",
            iconUrl = "https://example.com/icon.png",
        )

        val app = mapper.toDomain(dto)

        assertEquals("id-1", app.id)
        assertEquals("QuickNote", app.name)
        assertEquals("Notes app", app.description)
        assertEquals(Category.PRODUCTIVITY, app.category)
        assertEquals("https://example.com/icon.png", app.iconUrl)
    }

    @Test
    fun itemToDomain_mapsEveryKnownCategory() {
        val expected = mapOf(
            "Бизнес" to Category.BUSINESS,
            "Еда и напитки" to Category.FOOD,
            "Здоровье и фитнес" to Category.HEALTH,
            "Игры" to Category.GAME,
            "Книги и справочники" to Category.BOOKS,
            "Музыка" to Category.MUSIC,
            "Навигация" to Category.NAVIGATION,
            "Новости" to Category.NEWS,
            "Образ жизни" to Category.LIFESTYLE,
            "Образование" to Category.EDUCATION,
            "Общение" to Category.COMMUNICATION,
            "Погода" to Category.WEATHER,
            "Производительность" to Category.PRODUCTIVITY,
            "Развлечения" to Category.ENTERTAINMENT,
            "Утилиты" to Category.UTILITIES,
            "Финансы" to Category.FINANCE,
            "Фото и видео" to Category.PHOTO_VIDEO,
            "Шопинг" to Category.SHOPPING,
        )

        expected.forEach { (name, category) ->
            assertEquals(name, category, mapper.toDomain(appItemDto("id", category = name)).category)
        }
    }

    @Test
    fun itemToDomain_unknownCategory_mapsToOther() {
        val app = mapper.toDomain(appItemDto("id", category = "Космос"))

        assertEquals(Category.OTHER, app.category)
    }

    @Test
    fun itemToDomain_emptyCategory_mapsToOther() {
        val app = mapper.toDomain(appItemDto("id", category = ""))

        assertEquals(Category.OTHER, app.category)
    }

    @Test
    fun itemToDomain_categoryIsCaseSensitive() {
        val app = mapper.toDomain(appItemDto("id", category = "игры"))

        assertEquals(Category.OTHER, app.category)
    }

    @Test
    fun itemToDomain_keepsEmptyTextFields() {
        val app = mapper.toDomain(appItemDto("id", name = "", description = ""))

        assertEquals("", app.name)
        assertEquals("", app.description)
    }

    @Test
    fun detailsToDomain_mapsAllFields() {
        val dto = appDetailsDto(
            id = "id-1",
            name = "QuickNote",
            developer = "QuickSoft Inc.",
            category = "Здоровье и фитнес",
            ageRating = 3,
            size = 8.2,
            iconUrl = "https://example.com/icon.png",
            screenshotUrlList = listOf("https://example.com/1.png", "https://example.com/2.png"),
            description = "Long description",
        )

        val details = mapper.toDomain(dto)

        assertEquals("id-1", details.id)
        assertEquals("QuickNote", details.name)
        assertEquals("QuickSoft Inc.", details.developer)
        assertEquals(Category.HEALTH, details.category)
        assertEquals(3, details.ageRating)
        assertEquals("https://example.com/icon.png", details.iconUrl)
        assertEquals("Long description", details.description)
    }

    @Test
    fun detailsToDomain_convertsSizeToFloat() {
        val details = mapper.toDomain(appDetailsDto("id", size = 223.7))

        assertEquals(223.7f, details.size, 0.0001f)
    }

    @Test
    fun detailsToDomain_keepsScreenshotsOrder() {
        val urls = listOf("c", "a", "b")

        val details = mapper.toDomain(appDetailsDto("id", screenshotUrlList = urls))

        assertEquals(urls, details.screenshotUrlList)
    }

    @Test
    fun detailsToDomain_emptyScreenshots_givesEmptyList() {
        val details = mapper.toDomain(appDetailsDto("id", screenshotUrlList = emptyList()))

        assertTrue(details.screenshotUrlList.isEmpty())
    }

    @Test
    fun detailsToDomain_unknownCategory_mapsToOther() {
        val details = mapper.toDomain(appDetailsDto("id", category = "???"))

        assertEquals(Category.OTHER, details.category)
    }

    @Test
    fun detailsDto_missingScreenshotsInJson_defaultsToEmptyList() {
        val dto = AppDetailsDto(
            id = "id",
            name = "n",
            developer = "d",
            category = "Игры",
            ageRating = 0,
            size = 1.0,
            iconUrl = "u",
            description = "desc",
        )

        assertTrue(mapper.toDomain(dto).screenshotUrlList.isEmpty())
    }
}
