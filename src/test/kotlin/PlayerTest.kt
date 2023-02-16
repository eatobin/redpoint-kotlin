import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

val player: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))

class PlayerTest : StringSpec({
    "player should return its playerName" {
        player.playerName.shouldBe("Ringo Starr")
    }
})
//     "A Player should return its giftHistory" {
//         player.giftHistory.shouldBe(listOf(GiftPair("JohLen", "GeoHar")))
//     }

//     "A Player should return an updated giftHistory" {
//         setGiftHistory(player, listOf(GiftPair("nope", "yup"))).shouldBe(
//             Player(
//                 "Ringo Starr",
//                 listOf(GiftPair("nope", "yup"))
//             )
//         )
//     }

//     "A Player should return an extended giftHistory" {
//         addYearPlayer(player, "mee").shouldBe(
//             Player(
//                 "Ringo Starr",
//                 listOf(
//                     GiftPair("JohLen", "GeoHar"),
//                     GiftPair("mee", "mee")
//                 )
//             )
//         )
//     }
//})
