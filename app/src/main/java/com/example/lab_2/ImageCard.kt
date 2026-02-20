package com.example.lab_2

data class ImageCard(
    val imageUrl: String,
    val title: String,
    val description: String,
    val country: String,
)

fun getWorldLandmarks(): List<ImageCard> = listOf(
    ImageCard(
        imageUrl = "https://putidorogi-nn.ru/images/stories/evropa/franciya/eyfeleva_bashnya_5.jpg",
        title = "Эйфелева башня",
        description = "Символ Парижа и всей Франции, одна из самых узнаваемых построек мира",
        country = "Франция"
    ),
    ImageCard(
        imageUrl = "https://mytravelry.com/ru/wp-content/uploads/sites/2/2017/07/%D0%BA%D0%BE%D0%BB%D0%B8%D0%B7%D0%B5%D0%B9-%D0%BA%D0%B0%D1%81%D1%81%D1%8B.jpg",
        title = "Колизей",
        description = "Древнеримский амфитеатр, символ величия Римской империи",
        country = "Италия"
    ),
    ImageCard(
        imageUrl = "https://fs.tonkosti.ru/33/ol/33olc2eyzj6s4w8ksg8kkc0gg.jpg",
        title = "Статуя Свободы",
        description = "Подарок Франции США, символ свободы и демократии",
        country = "США"
    ),
    ImageCard(
        imageUrl = "https://cdn.radiosputnik.ru/images/152923/47/1529234700_0:0:1920:1080_1920x1080_80_0_0_ba217e637815132823c4f2fdb3817f5d.jpg",
        title = "Тадж-Махал",
        description = "Мавзолей из белого мрамора, признанный шедевром могольской архитектуры",
        country = "Индия"
    ),
    ImageCard(
        imageUrl = "https://wikipoints.ru/photos/middle/911_781c2.jpg",
        title = "Великая Китайская стена",
        description = "Самое длинное сооружение в мире, видно даже из космоса (на самом деле нет)",
        country = "Китай"
    ),
    ImageCard(
        imageUrl = "https://vsevgory.com/wp-content/uploads/peru-960x1149.jpg",
        title = "Мачу-Пикчу",
        description = "Затерянный город инков в Андах",
        country = "Перу"
    ),
    ImageCard(
        imageUrl = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/13/93/a7/be/sydney-opera-house.jpg",
        title = "Сиднейский оперный театр",
        description = "Футуристическое здание на берегу гавани Сиднея",
        country = "Австралия"
    ),
    ImageCard(
        imageUrl = "https://putidorogi-nn.ru/images/stories/evropa/vatikan/sobor_svyatogo_petra_2.jpg",
        title = "Храм Святого Петра",
        description = "Самый большой католический собор в мире",
        country = "Ватикан"
    ),
    ImageCard(
        imageUrl = "https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/847fa3b9-9c4d-4ff6-bb71-38e1f5e8f0be/-/format/webp/-/resize/1300x/",
        title = "Акрополь",
        description = "Древнегреческий архитектурный комплекс, символ классической Греции",
        country = "Греция"
    ),
    ImageCard(
        imageUrl = "https://whc.unesco.org/uploads/thumbs/site_0326_0044-594-0-20151104133439.jpg",
        title = "Петра",
        description = "Скальный город набатеев, высеченный в скалах",
        country = "Иордания"
    )
)