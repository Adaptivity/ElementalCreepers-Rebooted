package net.lomeli.ec.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import net.minecraftforge.oredict.OreDictionary;

import net.lomeli.ec.lib.ECVars;

public class EntityStoneCreeper extends EntityBaseCreeper {
    private List<Block> blockList = new ArrayList<Block>();

    public EntityStoneCreeper(World par1World) {
        super(par1World);
    }

    @Override
    public void explosion(int power, boolean flag) {
        genList();
        int radius = getPowered() ? (ECVars.stoneCreeperRadius * power) : ECVars.stoneCreeperRadius;
        for (int x = -radius; x <= radius; x++)
            for (int y = -radius; y <= radius; y++)
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z);
                    IBlockState blockState = worldObj.getBlockState(pos);
                    if (blockState != null && blockState.getBlock() != null) {
                        Block bk = blockState.getBlock();
                        if (bk != null && this.blockList.contains(bk) && Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius) {
                            bk.dropBlockAsItem(worldObj, pos, blockState, 0);
                            worldObj.setBlockToAir(pos);
                            bk.onBlockDestroyedByExplosion(worldObj, pos, new Explosion(worldObj, this, 0.0D, 0.0D, 0.0D, 0.0F, Collections.emptyList()));
                        }
                    }
                }
    }

    public void genList() {
        blockList.clear();
        for (ItemStack stack : OreDictionary.getOres("cobblestone")) {
            blockList.add(Block.getBlockFromItem(stack.getItem()));
        }
        for (ItemStack stack : OreDictionary.getOres("stone")) {
            blockList.add(Block.getBlockFromItem(stack.getItem()));
        }
        blockList.add(Blocks.mossy_cobblestone);
        blockList.add(Blocks.stone_brick_stairs);
        blockList.add(Blocks.stone_button);
        blockList.add(Blocks.stone_pressure_plate);
        blockList.add(Blocks.stone_stairs);
        blockList.add(Blocks.stonebrick);
        blockList.add(Blocks.stone_slab);
        blockList.add(Blocks.cobblestone_wall);
        blockList.add(Blocks.double_stone_slab);
    }

}
