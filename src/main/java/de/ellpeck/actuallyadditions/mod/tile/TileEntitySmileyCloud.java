/*
 * This file ("TileEntitySmileyCloud.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.tile;

import de.ellpeck.actuallyadditions.mod.network.gui.IStringReactor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntitySmileyCloud extends TileEntityBase implements IStringReactor{

    public String name;
    private String nameBefore;

    public TileEntitySmileyCloud(){
        super("smileyCloud");
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, NBTType type){
        super.writeSyncableNBT(compound, type);
        if(this.name != null && type != NBTType.SAVE_BLOCK){
            compound.setString("Name", this.name);
        }
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, NBTType type){
        super.readSyncableNBT(compound, type);
        if(type != NBTType.SAVE_BLOCK){
            this.name = compound.getString("Name");
        }
    }

    @Override
    public void updateEntity(){
        super.updateEntity();
        if(!this.worldObj.isRemote){
            boolean nameChanged = this.name != null ? !this.name.equals(this.nameBefore) : this.nameBefore != null;
            if(nameChanged && this.sendUpdateWithInterval()){
                this.nameBefore = this.name;
                this.markDirty();
            }
        }
    }

    @Override
    public void onTextReceived(String text, int textID, EntityPlayer player){
        this.name = text;
    }

    public void setStatus(boolean pinkAndFluffy){
        IBlockState state = this.worldObj.getBlockState(this.pos);
        Block block = state.getBlock();
        int meta = block.getMetaFromState(state);
        if(pinkAndFluffy){
            if(meta <= 3){
                this.worldObj.setBlockState(this.pos, block.getStateFromMeta(meta+4), 2);
            }
        }
        else{
            if(meta >= 4){
                this.worldObj.setBlockState(this.pos, block.getStateFromMeta(meta-4), 2);
            }
        }
    }
}
