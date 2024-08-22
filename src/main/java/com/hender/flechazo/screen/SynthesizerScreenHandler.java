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
                return stack.getItem() == ModItems.HENDER_CORE
                        || stack.getItem() == ModItems.HENDER_HELMET_CORE
                        || stack.getItem() == ModItems.HENDER_CHESTPLATE_CORE
                        || stack.getItem() == ModItems.HENDER_LEGGINGS_CORE
                        || stack.getItem() == ModItems.HENDER_BOOTS_CORE;
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
    public int getScaledProgress(int progressIndex, int progressArrowSize, int minProgress, int maxProgressDivider) {
        int progress = propertyDelegate.get(progressIndex);
        int maxProgress = propertyDelegate.get(progressIndex + 1);

        if (maxProgress == 0 || progress == 0) {
            return 0;
        }

        if (minProgress > 0 && progress <= maxProgress * minProgress / maxProgressDivider) {
            return progress * progressArrowSize / maxProgress;
        }

        if (maxProgressDivider > 0 && progress >= maxProgress * minProgress / maxProgressDivider) {
            return progress * progressArrowSize / maxProgress;
        }

        return 0;
    }

    public int getScaledProgress1() {
        return getScaledProgress(0, 16, 0, 0);
    }

    public int getScaledProgress2() {
        return getScaledProgress(0, 45, 0, 0);
    }

    public int getScaledProgress3() {
        return getScaledProgress(0, 29, 24, 53);
    }

    public int getScaledProgress4() {
        return getScaledProgress(0, 24, 24, 53);
    }
}

