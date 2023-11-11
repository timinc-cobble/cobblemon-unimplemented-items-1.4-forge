package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class DryRoot : PokemonItem(
    Properties()
) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: Player,
        target: PokemonEntity,
        pokemon: Pokemon
    ): InteractionResult {
        var changedOne = false
        pokemon.evs.forEach{entry ->
            if (entry.value > .0) {
                changedOne = true
                pokemon.evs[entry.key] = 0
            }
        }

        if (!changedOne) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyHasZeroEvs))
            return InteractionResult.FAIL
        }

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}