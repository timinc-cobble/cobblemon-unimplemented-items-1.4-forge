package us.timinc.mc.cobblemon.unimplementeditems

import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.item.group.CobblemonItemGroups
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ComposterBlock
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.entries.LootTableReference
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.event.LootTableLoadEvent
import net.minecraftforge.event.server.ServerStartedEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegisterEvent
import us.timinc.mc.cobblemon.unimplementeditems.blocks.UnimplementedItemsBlocks
import us.timinc.mc.cobblemon.unimplementeditems.items.PostBattleItem
import us.timinc.mc.cobblemon.unimplementeditems.items.UnimplementedItemsItems

@Mod(UnimplementedItems.MOD_ID)
object UnimplementedItems {
    const val MOD_ID = "unimplemented_items"

    val INJECTED_LOOTS = hashSetOf(
        BuiltInLootTables.FISHING_TREASURE, Blocks.GRASS.lootTable
    )

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    object UnimplementedItemsMod {
        @SubscribeEvent
        fun register(event: RegisterEvent) {
            event.register(ForgeRegistries.Keys.ITEMS) {
                it.register(myResourceLocation("ability_capsule"), UnimplementedItemsItems.ABILITY_CAPSULE)
                it.register(myResourceLocation("bottle_cap_atk"), UnimplementedItemsItems.BOTTLE_CAP_ATK)
                it.register(myResourceLocation("bottle_cap_def"), UnimplementedItemsItems.BOTTLE_CAP_DEF)
                it.register(myResourceLocation("bottle_cap_sa"), UnimplementedItemsItems.BOTTLE_CAP_SA)
                it.register(myResourceLocation("bottle_cap_sd"), UnimplementedItemsItems.BOTTLE_CAP_SD)
                it.register(myResourceLocation("bottle_cap_spd"), UnimplementedItemsItems.BOTTLE_CAP_SPD)
                it.register(myResourceLocation("bottle_cap_hp"), UnimplementedItemsItems.BOTTLE_CAP_HP)
                it.register(myResourceLocation("bottle_cap"), UnimplementedItemsItems.BOTTLE_CAP)
                it.register(myResourceLocation("bottle_cap_gold"), UnimplementedItemsItems.BOTTLE_CAP_GOLD)
                it.register(myResourceLocation("potion"), UnimplementedItemsItems.POTION)
                it.register(myResourceLocation("potion_hyper"), UnimplementedItemsItems.POTION_HYPER)
                it.register(myResourceLocation("potion_max"), UnimplementedItemsItems.POTION_MAX)
                it.register(myResourceLocation("ether"), UnimplementedItemsItems.ETHER)
                it.register(myResourceLocation("elixir"), UnimplementedItemsItems.ELIXIR)
                it.register(myResourceLocation("ability_patch"), UnimplementedItemsItems.ABILITY_PATCH)
                it.register(myResourceLocation("dry_root"), UnimplementedItemsItems.DRY_ROOT)
                it.register(myResourceLocation("power_weight"), UnimplementedItemsItems.POWER_WEIGHT)
                it.register(myResourceLocation("power_bracer"), UnimplementedItemsItems.POWER_BRACER)
                it.register(myResourceLocation("power_belt"), UnimplementedItemsItems.POWER_BELT)
                it.register(myResourceLocation("power_lens"), UnimplementedItemsItems.POWER_LENS)
                it.register(myResourceLocation("power_band"), UnimplementedItemsItems.POWER_BAND)
                it.register(myResourceLocation("power_anklet"), UnimplementedItemsItems.POWER_ANKLET)

                it.register(myResourceLocation("repel"), BlockItem(UnimplementedItemsBlocks.REPEL, Item.Properties()))

                ComposterBlock.COMPOSTABLES.put(UnimplementedItemsItems.DRY_ROOT, 0.3F)
            }

            event.register(ForgeRegistries.Keys.BLOCKS) {
                it.register(myResourceLocation("repel"), UnimplementedItemsBlocks.REPEL)
            }
        }

        @SubscribeEvent
        fun buildContents(event: BuildCreativeModeTabContentsEvent) {
            if (event.tabKey === CobblemonItemGroups.CONSUMABLES_KEY) {
                event.accept(UnimplementedItemsItems.ABILITY_CAPSULE)
                event.accept(UnimplementedItemsItems.ABILITY_PATCH)
                event.accept(UnimplementedItemsItems.BOTTLE_CAP_ATK)
                event.accept(UnimplementedItemsItems.BOTTLE_CAP_DEF)
                event.accept(UnimplementedItemsItems.BOTTLE_CAP_SA)
                event.accept(UnimplementedItemsItems.BOTTLE_CAP_SD)
                event.accept(UnimplementedItemsItems.BOTTLE_CAP_SPD)
                event.accept(UnimplementedItemsItems.BOTTLE_CAP_HP)
                event.accept(UnimplementedItemsItems.BOTTLE_CAP_GOLD)
            }

            if (event.tabKey === CreativeModeTabs.INGREDIENTS) {
                event.accept(UnimplementedItemsItems.BOTTLE_CAP)
            }

            if (event.tabKey === CreativeModeTabs.TOOLS_AND_UTILITIES) {
                event.accept(UnimplementedItemsItems.POTION)
                event.accept(UnimplementedItemsItems.POTION_HYPER)
                event.accept(UnimplementedItemsItems.POTION_MAX)
                event.accept(UnimplementedItemsItems.ELIXIR)
                event.accept(UnimplementedItemsItems.ETHER)
                event.accept(UnimplementedItemsItems.DRY_ROOT)
                event.accept(UnimplementedItemsItems.POWER_ANKLET)
                event.accept(UnimplementedItemsItems.POWER_BAND)
                event.accept(UnimplementedItemsItems.POWER_BELT)
                event.accept(UnimplementedItemsItems.POWER_LENS)
                event.accept(UnimplementedItemsItems.POWER_BRACER)
                event.accept(UnimplementedItemsItems.POWER_WEIGHT)
            }

            if (event.tabKey === CreativeModeTabs.FUNCTIONAL_BLOCKS) {
                event.accept(UnimplementedItemsBlocks.REPEL)
            }
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    object UnimplementedItemsForge {
        @SubscribeEvent
        fun onInit(e: ServerStartedEvent) {
            CobblemonEvents.BATTLE_VICTORY.subscribe { event ->
                val ownedPokemon = event.battle.actors.flatMap { it.pokemonList }.map { it.originalPokemon }
                    .filter { it.isPlayerOwned() }
                ownedPokemon.forEach { pokemon ->
                    val heldItemStack = pokemon.heldItem()
                    val heldItem = heldItemStack.item
                    if (heldItem is PostBattleItem) {
                        heldItem.doPostBattle(heldItemStack, pokemon, event)
                    }
                }
            }
        }

        @SubscribeEvent
        fun onLoadLootTable(e: LootTableLoadEvent) {
            if (!INJECTED_LOOTS.contains(e.name)) {
                return
            }

            e.table.addPool(
                LootPool.Builder()
                    .add(LootTableReference.lootTableReference(ResourceLocation(MOD_ID, "injection/${e.name.path}")))
                    .build()
            )
        }
    }

    fun myResourceLocation(str: String): ResourceLocation {
        return ResourceLocation(MOD_ID, str)
    }
}