@file:Suppress("MemberVisibilityCanBePrivate")

package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.pokemon.stats.Stats
import net.minecraft.world.item.Item

object UnimplementedItemsItems {
    val ABILITY_CAPSULE = AbilityCapsule()
    val BOTTLE_CAP_ATK = BottleCap(Stats.ATTACK)
    val BOTTLE_CAP_DEF = BottleCap(Stats.DEFENCE)
    val BOTTLE_CAP_SA = BottleCap(Stats.SPECIAL_ATTACK)
    val BOTTLE_CAP_SD = BottleCap(Stats.SPECIAL_DEFENCE)
    val BOTTLE_CAP_SPD = BottleCap(Stats.SPEED)
    val BOTTLE_CAP_HP = BottleCap(Stats.HP)
    val BOTTLE_CAP = Item(Item.Properties().stacksTo(16))
    val BOTTLE_CAP_GOLD = BottleCap(null)
    val POTION = Item(Item.Properties())
    val POTION_HYPER = Item(Item.Properties())
    val POTION_MAX = Item(Item.Properties())
    val ELIXIR = Item(Item.Properties())
    val ETHER = Item(Item.Properties())
    val ABILITY_PATCH = AbilityPatch()
    val DRY_ROOT = DryRoot()
    val POWER_WEIGHT = Item(Item.Properties())
    val POWER_BRACER = Item(Item.Properties())
    val POWER_BELT = Item(Item.Properties())
    val POWER_LENS = Item(Item.Properties())
    val POWER_BAND = Item(Item.Properties())
    val POWER_ANKLET = Item(Item.Properties())
}