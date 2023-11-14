package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class AbilityCapsule : PokemonItem(Properties().stacksTo(16)) {
    override fun processInteraction(
        itemStack: ItemStack, player: Player, target: PokemonEntity, pokemon: Pokemon
    ): InteractionResult {
        if (pokemon.ability.priority == Priority.LOW) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyHasHiddenAbility))
            return InteractionResult.FAIL
        }

        val form = target.form
        val potentialAbilityPriorityPool = form.abilities.mapping[Priority.LOWEST]!!
        if (potentialAbilityPriorityPool.size == 1) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.onlyOneCommonAbility))
            return InteractionResult.FAIL
        }

        val potentialAbility = potentialAbilityPriorityPool[1 - pokemon.ability.index]

        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 1 - pokemon.ability.index
        newAbility.priority = pokemon.ability.priority
        pokemon.ability = newAbility

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}