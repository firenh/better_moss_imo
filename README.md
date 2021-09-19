# Better Moss IMO
## (IMO stands for In My Opinion in case you're old and not hip with the times)

## Moss Blocks don't generate azaleas and flowering azaleas when bone mealed.

This is a feature I really want implemented into the base game. Right now, if moss blocks generate in any other part of the world other than Lush Caves through a world gen mod, it just seems really odd to have azaleas generate with it. Or, if they don't generate with azaleas, they will still spawn them with bonemealed. With this mod, moss will only spawn grass, tall grass, and moss carpets when bonemealed.

## What about azaleas though?

Azaleas can still be obtained! When an azalea tree's leaves decay, azaleas drop like saplings. This is a part of the vanilla game, however because of the moss mechanic there really isn't a need to do this.

## Okay, but still; how do you get azaleas now?

Two world gen features have been added with this mod: Moss patches generate randomly throughout the world, and Azalea Trees spawn randomly throughout the world. Moss patches have a 1/20 chance to spawn per chunk, and Azalea Trees have a 1/1024 chance to spawn. The azalea tree chance may seem extremely low, but it equates to about one Azalea Tree for a 32 by 32 chunk area, which I find decently reasonable.

For now there's no config to turn this off or change the chances, but I'm planning on it for the future.

## What if I do want to disable the worldgen features?

If you want to disable the worldgen features, for now you need to know the basics of modding. Clone this repo or whatever the thing is to copy it (I'm new to Github lmao) and make your way over to src/main/java/fireopal/bettermoss/BetterMossIMO.java and delete the lines at the bottom that begin with `BiomeModifications`
