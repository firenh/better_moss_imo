package fireopal.bettermoss;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPatchFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.BendingTrunkPlacer;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class BetterMossIMO implements ModInitializer {
	private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
		return (ConfiguredFeature)Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("better_moss_imo", id), configuredFeature);
	}

	static DataPool.Builder<BlockState> pool() {
		return DataPool.builder();
	}

	public static ConfiguredFeature<SimpleBlockFeatureConfig, ?> MOSS_VEGETATION_BONEMEAL;
	public static ConfiguredFeature<VegetationPatchFeatureConfig, ?> MOSS_PATCH_BONEMEAL_NEW;

	public static ConfiguredFeature<VegetationPatchFeatureConfig, ?> MOSS_PATCH_SURFACE = (ConfiguredFeature<VegetationPatchFeatureConfig, ?>) Feature.VEGETATION_PATCH.configure(new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE.getId(), new SimpleBlockStateProvider(Blocks.MOSS_BLOCK.getDefaultState()), () -> {
		return MOSS_VEGETATION_BONEMEAL;
	}, VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0.0F, 5, 0.6F, UniformIntProvider.create(1, 2), 0.75F))
		.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.WORLD_SURFACE_WG))).spreadHorizontally().applyChance(20);

	public static ConfiguredFeature<?, ?> AZALEA_TREE_NEW_SURFACE = ConfiguredFeatures.AZALEA_TREE.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.WORLD_SURFACE_WG))).spreadHorizontally().applyChance(1024);

	RegistryKey<ConfiguredFeature<?, ?>> mossPatchSurface = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
        new Identifier("better_moss_imo", "moss_patch_surface"));

	RegistryKey<ConfiguredFeature<?, ?>> azaleaTreeSurface = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
        new Identifier("better_moss_imo", "azalea_tree_surface"));

	@Override
	public void onInitialize() {
		MOSS_VEGETATION_BONEMEAL = register("moss_vegetation_bonemeal", Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(pool().add(Blocks.MOSS_CARPET.getDefaultState(), 25).add(Blocks.GRASS.getDefaultState(), 50).add(Blocks.TALL_GRASS.getDefaultState(), 10)))));

		MOSS_PATCH_BONEMEAL_NEW = register("moss_patch_bonemeal_new", Feature.VEGETATION_PATCH.configure(new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE.getId(), new SimpleBlockStateProvider(Blocks.MOSS_BLOCK.getDefaultState()), () -> {
			return MOSS_VEGETATION_BONEMEAL;
		}, VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0.0F, 5, 0.6F, UniformIntProvider.create(1, 2), 0.75F)));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, mossPatchSurface.getValue(), MOSS_PATCH_SURFACE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, azaleaTreeSurface.getValue(), AZALEA_TREE_NEW_SURFACE);

		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, mossPatchSurface);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, azaleaTreeSurface);
	}
}
