package com.github.crisacm.animefinder.data.api.model

data class AnimeResponse(
  val id: Long
)

/*
{
  "data": {
    "mal_id": 52991,
    "url": "https://myanimelist.net/anime/52991/Sousou_no_Frieren",
    "images": {
      "jpg": {
        "image_url": "https://cdn.myanimelist.net/images/anime/1015/138006.jpg",
        "small_image_url": "https://cdn.myanimelist.net/images/anime/1015/138006t.jpg",
        "large_image_url": "https://cdn.myanimelist.net/images/anime/1015/138006l.jpg"
      },
      "webp": {
        "image_url": "https://cdn.myanimelist.net/images/anime/1015/138006.webp",
        "small_image_url": "https://cdn.myanimelist.net/images/anime/1015/138006t.webp",
        "large_image_url": "https://cdn.myanimelist.net/images/anime/1015/138006l.webp"
      }
    },
    "trailer": {
      "youtube_id": "ZEkwCGJ3o7M",
      "url": "https://www.youtube.com/watch?v=ZEkwCGJ3o7M",
      "embed_url": "https://www.youtube.com/embed/ZEkwCGJ3o7M?enablejsapi=1&wmode=opaque&autoplay=1",
      "images": {
        "image_url": "https://img.youtube.com/vi/ZEkwCGJ3o7M/default.jpg",
        "small_image_url": "https://img.youtube.com/vi/ZEkwCGJ3o7M/sddefault.jpg",
        "medium_image_url": "https://img.youtube.com/vi/ZEkwCGJ3o7M/mqdefault.jpg",
        "large_image_url": "https://img.youtube.com/vi/ZEkwCGJ3o7M/hqdefault.jpg",
        "maximum_image_url": "https://img.youtube.com/vi/ZEkwCGJ3o7M/maxresdefault.jpg"
      }
    },
    "approved": true,
    "titles": [
      {
        "type": "Default",
        "title": "Sousou no Frieren"
      },
      {
        "type": "Synonym",
        "title": "Frieren at the Funeral"
      },
      {
        "type": "Japanese",
        "title": "葬送のフリーレン"
      },
      {
        "type": "English",
        "title": "Frieren: Beyond Journey's End"
      }
    ],
    "title": "Sousou no Frieren",
    "title_english": "Frieren: Beyond Journey's End",
    "title_japanese": "葬送のフリーレン",
    "title_synonyms": [
      "Frieren at the Funeral"
    ],
    "type": "TV",
    "source": "Manga",
    "episodes": 28,
    "status": "Finished Airing",
    "airing": false,
    "aired": {
      "from": "2023-09-29T00:00:00+00:00",
      "to": "2024-03-22T00:00:00+00:00",
      "prop": {
        "from": {
          "day": 29,
          "month": 9,
          "year": 2023
        },
        "to": {
          "day": 22,
          "month": 3,
          "year": 2024
        }
      },
      "string": "Sep 29, 2023 to Mar 22, 2024"
    },
    "duration": "24 min per ep",
    "rating": "PG-13 - Teens 13 or older",
    "score": 9.34,
    "scored_by": 459003,
    "rank": 1,
    "popularity": 232,
    "members": 815713,
    "favorites": 47733,
    "synopsis": "During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them.\n\nHowever, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her \"usual\" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through.\n\nAs the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.\n\n[Written by MAL Rewrite]",
    "background": "Sousou no Frieren was released on Blu-ray and DVD in seven volumes from January 24, 2024, to July 17, 2024.",
    "season": "fall",
    "year": 2023,
    "broadcast": {
      "day": "Fridays",
      "time": "23:00",
      "timezone": "Asia/Tokyo",
      "string": "Fridays at 23:00 (JST)"
    },
    "producers": [
      {
        "mal_id": 17,
        "type": "anime",
        "name": "Aniplex",
        "url": "https://myanimelist.net/anime/producer/17/Aniplex"
      },
      {
        "mal_id": 53,
        "type": "anime",
        "name": "Dentsu",
        "url": "https://myanimelist.net/anime/producer/53/Dentsu"
      },
      {
        "mal_id": 62,
        "type": "anime",
        "name": "Shogakukan-Shueisha Productions",
        "url": "https://myanimelist.net/anime/producer/62/Shogakukan-Shueisha_Productions"
      },
      {
        "mal_id": 1003,
        "type": "anime",
        "name": "Nippon Television Network",
        "url": "https://myanimelist.net/anime/producer/1003/Nippon_Television_Network"
      },
      {
        "mal_id": 1143,
        "type": "anime",
        "name": "TOHO animation",
        "url": "https://myanimelist.net/anime/producer/1143/TOHO_animation"
      },
      {
        "mal_id": 1430,
        "type": "anime",
        "name": "Shogakukan",
        "url": "https://myanimelist.net/anime/producer/1430/Shogakukan"
      }
    ],
    "licensors": [],
    "studios": [
      {
        "mal_id": 11,
        "type": "anime",
        "name": "Madhouse",
        "url": "https://myanimelist.net/anime/producer/11/Madhouse"
      }
    ],
    "genres": [
      {
        "mal_id": 2,
        "type": "anime",
        "name": "Adventure",
        "url": "https://myanimelist.net/anime/genre/2/Adventure"
      },
      {
        "mal_id": 8,
        "type": "anime",
        "name": "Drama",
        "url": "https://myanimelist.net/anime/genre/8/Drama"
      },
      {
        "mal_id": 10,
        "type": "anime",
        "name": "Fantasy",
        "url": "https://myanimelist.net/anime/genre/10/Fantasy"
      }
    ],
    "explicit_genres": [],
    "themes": [],
    "demographics": [
      {
        "mal_id": 27,
        "type": "anime",
        "name": "Shounen",
        "url": "https://myanimelist.net/anime/genre/27/Shounen"
      }
    ],
    "relations": [
      {
        "relation": "Adaptation",
        "entry": [
          {
            "mal_id": 126287,
            "type": "manga",
            "name": "Sousou no Frieren",
            "url": "https://myanimelist.net/manga/126287/Sousou_no_Frieren"
          }
        ]
      },
      {
        "relation": "Side Story",
        "entry": [
          {
            "mal_id": 56885,
            "type": "anime",
            "name": "Sousou no Frieren: ●● no Mahou",
            "url": "https://myanimelist.net/anime/56885/Sousou_no_Frieren__●●_no_Mahou"
          }
        ]
      },
      {
        "relation": "Other",
        "entry": [
          {
            "mal_id": 56805,
            "type": "anime",
            "name": "Yuusha",
            "url": "https://myanimelist.net/anime/56805/Yuusha"
          },
          {
            "mal_id": 58313,
            "type": "anime",
            "name": "Haru (2024)",
            "url": "https://myanimelist.net/anime/58313/Haru_2024"
          }
        ]
      }
    ],
    "theme": {
      "openings": [
        "1: \"Yuusha (勇者)\" by YOASOBI (eps 1-16)",
        "2: \"Haru (晴る)\" by Yorushika (ヨルシカ) (eps 17-28)"
      ],
      "endings": [
        "1: \"Anytime Anywhere\" by milet (eps 1-14,16-28)",
        "S1: \"bliss\" by milet (eps Special Broadcast: 1-4)"
      ]
    },
    "external": [
      {
        "name": "Official Site",
        "url": "https://frieren-anime.jp/"
      },
      {
        "name": "@Anime_Frieren",
        "url": "https://twitter.com/Anime_Frieren"
      },
      {
        "name": "AniDB",
        "url": "https://anidb.net/perl-bin/animedb.pl?show=anime&aid=17617"
      },
      {
        "name": "ANN",
        "url": "https://www.animenewsnetwork.com/encyclopedia/anime.php?id=26334"
      },
      {
        "name": "Wikipedia",
        "url": "https://en.wikipedia.org/wiki/Frieren#Anime"
      },
      {
        "name": "Wikipedia",
        "url": "https://ja.wikipedia.org/wiki/%E8%91%AC%E9%80%81%E3%81%AE%E3%83%95%E3%83%AA%E3%83%BC%E3%83%AC%E3%83%B3"
      },
      {
        "name": "Syoboi",
        "url": "https://cal.syoboi.jp/tid/6776"
      }
    ],
    "streaming": [
      {
        "name": "Crunchyroll",
        "url": "http://www.crunchyroll.com/series-283731"
      },
      {
        "name": "Netflix",
        "url": "https://www.netflix.com/"
      },
      {
        "name": "Aniplus TV",
        "url": "http://www.aniplustv.com/"
      },
      {
        "name": "Bahamut Anime Crazy",
        "url": "https://ani.gamer.com.tw/"
      },
      {
        "name": "Bilibili Global",
        "url": "https://www.bilibili.tv/en/index"
      },
      {
        "name": "CatchPlay",
        "url": "https://www.catchplay.com/"
      },
      {
        "name": "MeWatch",
        "url": "https://www.mewatch.sg/"
      },
      {
        "name": "Muse Asia",
        "url": "https://www.youtube.com/channel/UCGbshtvS9t-8CW11W7TooQg"
      },
      {
        "name": "iQIYI",
        "url": "https://www.iq.com/"
      }
    ]
  }
}
*/