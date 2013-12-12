package net.lomeli.ec.entity;

import net.lomeli.ec.lib.ECVars;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class EntityWaterCreeper extends EntityBaseCreeper {

    public EntityWaterCreeper(World par1World) {
        super(par1World);
        this.explosionRadius = ECVars.waterCreeperRadius;
    }

    @Override
    public void explosion(int power, boolean flag) {
        int radius = getPowered() ? (int) (this.explosionRadius * power) : this.explosionRadius;
        for (int x = -radius; x <= radius; x++)
            for (int y = -radius; y <= radius; y++)
                for (int z = -radius; z <= radius; z++) {
                    if (Block.waterStill.canPlaceBlockAt(worldObj, (int) posX + x, (int) posY + y, (int) posZ + z)
                            && !Block.waterStill.canPlaceBlockAt(worldObj, (int) posX + x, (int) posY + y - 1, (int) posZ + z)) {
                        if (rand.nextBoolean())
                            worldObj.setBlock((int) posX + x, (int) posY + y, (int) posZ + z, Block.waterMoving.blockID);
                    }
                }
    }
}
