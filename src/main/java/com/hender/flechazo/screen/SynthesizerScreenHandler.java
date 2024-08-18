package com.hender.flechazo.screen;

import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.block.entity.SynthesizerBlockEntity;
import com.hender.flechazo.data.SynthesizerData;
import com.hender.flechazo.item.ModItems;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import java.util.Objects;

public class SynthesizerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final SynthesizerBlockEntity blockEntity;

    public SynthesizerScreenHandler(int syncId, PlayerInventory playerInventory, PropertyDelegate propertyDelegate, BlockEntity blockEntity) {
        super(HenderScreenHandlers.SYNTHESIZER_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 5);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);

        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (SynthesizerBlockEntity) blockEntity;

        this.addSlot(new Slot(inventory, 0, 8, 53) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION || stack.getItem() == ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION;
            }
        });
        this.addSlot(new Slot(inventory, 1, 8, 17) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModBlocks.HENDER_TOOL_ORE.asItem();
            }
        });
        this.addSlot(new Slot(inventory, 2, 69, 17) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.HENDER_TOOL;
            }
        });
        this.addSlot(new Slot(inventory, 3, 109, 17) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.HENDER_CORE || stack.getItem() == ModItems.HENDER_CORE_HELMET || stack.getItem() == ModItems.HENDER_CORE_CHESTPLATE || stack.getItem() == ModItems.HENDER_CORE_LEGGINGS || stack.getItem() == ModItems.HENDER_CORE_BOOTS;
            }
        });
        this.addSlot(new Slot(inventory, 4, 152, 53) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == ModItems.HENDER_MACE
                        || stack.getItem() == ModItems.HENDER_TOOL_HELMET
                        || stack.getItem() == ModItems.HENDER_TOOL_CHESTPLATE
                        || stack.getItem() == ModItems.HENDER_TOOL_LEGGINGS
                        || stack.getItem() == ModItems.HENDER_TOOL_BOOTS;
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);


        addProperties(propertyDelegate);
    }

    public SynthesizerScreenHandler(int syncId, PlayerInventory playerInventory, SynthesizerData data){
        this(syncId, playerInventory, new ArrayPropertyDelegate(5), playerInventory.player.getWorld().getBlockEntity(data.pos()));
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot invSlot = this.slots.get(slot);
        if (invSlot != null && invSlot.hasStack()) {
            ItemStack originalStack = invSlot.getStack();
            newStack = originalStack.copy();
            if (slot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                invSlot.setStack(ItemStack.EMPTY);
            } else {
                invSlot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public boolean isCrafting(){
        return propertyDelegate.get(0) > 0;
    }

    public boolean isRaining(){
        return Objects.requireNonNull(blockEntity.getWorld()).isRaining();
    }
        public int getScaledProgress1(){
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int progressArrowSize = 16;
        return maxProgress !=0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
        }
        public int getScaledProgress2(){
        int progress2 = propertyDelegate.get(0);
        int maxProgress2 = propertyDelegate.get(1);
        int progressArrowSize2 = 45;
        return maxProgress2 != 0 && progress2 != 0 ? progress2 * progressArrowSize2 / maxProgress2 : 0;
    }
    public int getScaledProgress3(){
        int progress3 = propertyDelegate.get(0);
        int maxProgress3 = propertyDelegate.get(1);
        int progressArrowSize3 = 29;
        return maxProgress3 != 0 && progress3 != 0 && progress3 <= maxProgress3 * 24/53 ? progress3 * progressArrowSize3 / maxProgress3 : 0;
    }
    public int getScaledProgress4(){
        int progress4 = propertyDelegate.get(0);
        int maxProgress4 = propertyDelegate.get(1);
        int progressArrowSize4 = 24;
        return maxProgress4 != 0 && progress4 != 0 && progress4 >= maxProgress4 * 24/53 ? progress4 * progressArrowSize4 / maxProgress4 : 0;
    }
}

