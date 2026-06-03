package net.mokus.wathextras.block.custom;

import io.wifi.starrailexpress.game.GameConstants;
import io.wifi.starrailexpress.game.GameUtils;
import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Item.TooltipType;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.List;

public class KillBlock extends BarrierBlock {
    public KillBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player player) {
            GameUtils.killPlayer(player, false, null, GameConstants.DeathReasons.FELL_OUT_OF_TRAIN);
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, Item.TooltipType options) {
        tooltip.add(Component.translatable("tooltip.wathextras.killblocks.block").withColor(0x7b9aba));
        super.appendHoverText(stack, context, tooltip, options);
    }
}
