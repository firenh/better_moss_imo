package fireopal.bettermoss;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class NewMossBlock extends Block implements Fertilizable {
    public NewMossBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.up()).isAir();
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        Feature.VEGETATION_PATCH.generate(new FeatureContext(world, world.getChunkManager().getChunkGenerator(), random, pos.up(), BetterMossIMO.MOSS_PATCH_BONEMEAL_NEW.getConfig()));
    }
}
