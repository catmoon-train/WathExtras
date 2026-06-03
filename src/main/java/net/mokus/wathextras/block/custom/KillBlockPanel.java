package net.mokus.wathextras.block.custom;

import io.wifi.starrailexpress.content.block.BarrierPanelBlock;
import io.wifi.starrailexpress.game.GameConstants;
import io.wifi.starrailexpress.game.GameUtils;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.List;

public class KillBlockPanel extends BarrierPanelBlock {

    public KillBlockPanel(Properties properties) {
        super(properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Player player) {
            GameUtils.killPlayer(player, false, null, GameConstants.DeathReasons.FELL_OUT_OF_TRAIN);
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag options) {
        tooltip.add(Component.translatable("tooltip.wathextras.killblocks.panel").withColor(0x7b9aba));
        super.appendHoverText(stack, context, tooltip, options);
    }
}
