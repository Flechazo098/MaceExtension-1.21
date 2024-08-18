package com.hender.flechazo.block.entity;

import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.data.SynthesizerData;
import com.hender.flechazo.item.ModItems;
import com.hender.flechazo.screen.SynthesizerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SynthesizerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<SynthesizerData>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 3;
    private static final int OUTPUT_SLOT_1 = 2;
    private static final int OUTPUT_SLOT_2 = 4;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 500;
    public int getMaxProgressForItem(ItemStack stack){
        if (stack.getItem() == ModItems.HENDER_TOOL) {
            return maxProgress = 114;
        } else if (stack.getItem() == ModItems.HENDER_MACE) {
            return maxProgress = 300;
        } else if (stack.getItem() == ModItems.HENDER_TOOL_HELMET) {
            return maxProgress = 180;
        } else if (stack.getItem() == ModItems.HENDER_TOOL_CHESTPLATE) {
            return maxProgress = 250;
        }else if (stack.getItem() == ModItems.HENDER_TOOL_LEGGINGS){
            return maxProgress = 220;
        } else if (stack.getItem() == ModItems.HENDER_TOOL_BOOTS) {
            return maxProgress = 150;
        }
        return 72;
    }




    public SynthesizerBlockEntity(BlockPos pos, BlockState state) {
        super(HenderBlockEntities.SYNTHESIZER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> SynthesizerBlockEntity.this.progress;
                    case 1 -> SynthesizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> SynthesizerBlockEntity.this.progress = value;
                    case 1 -> SynthesizerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.SynthesizerBlockEntity");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SynthesizerScreenHandler(syncId, playerInventory, this.propertyDelegate, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public SynthesizerData getScreenOpeningData(ServerPlayerEntity player) {
        return new SynthesizerData(pos);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, false, registryLookup);
        nbt.putInt("synthesizer", progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        progress = nbt.getInt("synthesizer");
    }

    @Override
    public int getMaxCount(ItemStack stack) {
        return 64;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) {
            return;
        }
        if (isOutputSlotAvailable()) {
            if (hasRecipe()) {
                increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {

        if (getStack(INPUT_SLOT_1).getItem() == ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION
                && getStack(INPUT_SLOT_2).getItem() == ModBlocks.HENDER_TOOL_ORE.asItem()) {

            ItemStack resultTool = new ItemStack(ModItems.HENDER_TOOL);
            System.out.println("Crafting HENDER_TOOL");
            craftOutput(OUTPUT_SLOT_1, resultTool, 1, INPUT_SLOT_2);
        }


        if (getStack(OUTPUT_SLOT_2).isEmpty()) {

            if (getStack(INPUT_SLOT_3).getItem() == ModItems.HENDER_CORE && getStack(OUTPUT_SLOT_1).getCount() >= 42) {
                ItemStack resultMace = new ItemStack(ModItems.HENDER_MACE);
                craftOutput(OUTPUT_SLOT_2, resultMace, 1, INPUT_SLOT_3);
                getStack(OUTPUT_SLOT_1).decrement(42);
            }


            if (getStack(INPUT_SLOT_3).getItem() == ModItems.HENDER_CORE_HELMET && getStack(OUTPUT_SLOT_1).getCount() >= 5) {
                ItemStack resultHelmet = new ItemStack(ModItems.HENDER_TOOL_HELMET);
                craftOutput(OUTPUT_SLOT_2, resultHelmet, 1, INPUT_SLOT_3);
                getStack(OUTPUT_SLOT_1).decrement(5);
            }
            if (getStack(INPUT_SLOT_3).getItem() == ModItems.HENDER_CORE_CHESTPLATE && getStack(OUTPUT_SLOT_1).getCount() >= 8) {
                ItemStack resultChestplate = new ItemStack(ModItems.HENDER_TOOL_CHESTPLATE);
                craftOutput(OUTPUT_SLOT_2, resultChestplate, 1, INPUT_SLOT_3);
                getStack(OUTPUT_SLOT_1).decrement(8);
            }
            if (getStack(INPUT_SLOT_3).getItem() == ModItems.HENDER_CORE_LEGGINGS && getStack(OUTPUT_SLOT_1).getCount() >= 7) {
                ItemStack resultLeggings = new ItemStack(ModItems.HENDER_TOOL_LEGGINGS);
                craftOutput(OUTPUT_SLOT_2, resultLeggings, 1, INPUT_SLOT_3);
                getStack(OUTPUT_SLOT_1).decrement(7);
            }
            if (getStack(INPUT_SLOT_3).getItem() == ModItems.HENDER_CORE_BOOTS && getStack(OUTPUT_SLOT_1).getCount() >= 4) {
                ItemStack resultBoots = new ItemStack(ModItems.HENDER_TOOL_BOOTS);
                craftOutput(OUTPUT_SLOT_2, resultBoots, 1, INPUT_SLOT_3);
                getStack(OUTPUT_SLOT_1).decrement(4);
            }
        }
    }

    private void craftOutput(int outputSlot, ItemStack result, int decrement, int inputSlot) {
        setStack(outputSlot, new ItemStack(result.getItem(), getStack(outputSlot).getCount() + result.getCount()));
        getStack(inputSlot).decrement(decrement);
    }


    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftProgress() {
        ItemStack outputItem = getStack(OUTPUT_SLOT_1).isEmpty() ? getStack(OUTPUT_SLOT_2) : getStack(OUTPUT_SLOT_1);
        this.maxProgress = getMaxProgressForItem(outputItem);
        this.progress++;
    }


    private boolean hasRecipe() {
        ItemStack input1 = getStack(INPUT_SLOT_1);
        ItemStack input2 = getStack(INPUT_SLOT_2);
        ItemStack input3 = getStack(INPUT_SLOT_3);
        ItemStack output1 = getStack(OUTPUT_SLOT_1);
        ItemStack output2 = getStack(OUTPUT_SLOT_2);

        boolean output1Full = output1.getCount() >= 64;
        boolean output2NotEmpty = !output2.isEmpty();

        boolean hasTransmutationPotion = input1.getItem() == ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION || input1.getItem() == ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION;
        boolean hasHenderOre = input2.getItem() == ModBlocks.HENDER_TOOL_ORE.asItem();

        boolean canCraftTool = !output1Full && hasTransmutationPotion && hasHenderOre && canInsertAmountIntoOutputSlot(new ItemStack(ModItems.HENDER_TOOL));
        boolean canCraftMace = !output2NotEmpty && input3.getItem() == ModItems.HENDER_CORE && output1.getCount() >= 42 && canInsertAmountIntoOutputSlot(new ItemStack(ModItems.HENDER_MACE));
        boolean canCraftHelmet = !output2NotEmpty && input3.getItem() == ModItems.HENDER_CORE_HELMET && output1.getCount() >= 5 && canInsertAmountIntoOutputSlot(new ItemStack(ModItems.HENDER_TOOL_HELMET));
        boolean canCraftChestplate = !output2NotEmpty && input3.getItem() == ModItems.HENDER_CORE_CHESTPLATE && output1.getCount() >= 8 && canInsertAmountIntoOutputSlot(new ItemStack(ModItems.HENDER_TOOL_CHESTPLATE));
        boolean canCraftLeggings = !output2NotEmpty && input3.getItem() == ModItems.HENDER_CORE_LEGGINGS && output1.getCount() >= 7 && canInsertAmountIntoOutputSlot(new ItemStack(ModItems.HENDER_TOOL_LEGGINGS));
        boolean canCraftBoots = !output2NotEmpty && input3.getItem() == ModItems.HENDER_CORE_BOOTS && output1.getCount() >= 4 && canInsertAmountIntoOutputSlot(new ItemStack(ModItems.HENDER_TOOL_BOOTS));

        return canCraftTool || canCraftMace || canCraftHelmet || canCraftChestplate || canCraftLeggings || canCraftBoots;
    }


    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
            if (result.getItem() == ModItems.HENDER_TOOL) {
            return this.getStack(OUTPUT_SLOT_1).getCount() + result.getCount() <= this.getMaxCountPerStack();
        } else if (result.getItem() == ModItems.HENDER_MACE) {
            return this.getStack(OUTPUT_SLOT_2).getCount() + result.getCount() <= this.getMaxCountPerStack();
        }
        return false;
    }


    private boolean isOutputSlotAvailable() {
        return (this.getStack(OUTPUT_SLOT_1).isEmpty() || this.getStack(OUTPUT_SLOT_1).getCount() < this.getMaxCountPerStack())
                || (this.getStack(OUTPUT_SLOT_2).isEmpty() || this.getStack(OUTPUT_SLOT_2).getCount() < this.getMaxCountPerStack());
    }
    private void updateFirstProgressBar() {
        ItemStack input1 = getStack(INPUT_SLOT_1);

        if (input1.getItem() == ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION) {
        if (this.progress == 0) {
            this.progress = this.maxProgress;
            getStack(INPUT_SLOT_1).decrement(1);
            setStack(INPUT_SLOT_1, new ItemStack(ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION));
        }
        this.progress--;
    }
}

    @Override
    public boolean canInsert(int slot, ItemStack stack) {
        if (slot == INPUT_SLOT_1) {
            return stack.getItem() == ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION || stack.getItem() == ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION;
        } else if (slot == INPUT_SLOT_2) {
            return stack.getItem() == ModBlocks.HENDER_TOOL_ORE.asItem();
        } else if (slot == INPUT_SLOT_3) {
            return stack.getItem() == ModItems.HENDER_CORE || stack.getItem() == ModItems.HENDER_CORE_HELMET || stack.getItem() == ModItems.HENDER_CORE_CHESTPLATE || stack.getItem() == ModItems.HENDER_CORE_LEGGINGS || stack.getItem() == ModItems.HENDER_CORE_BOOTS;
        } else if (slot == OUTPUT_SLOT_1) {
            return stack.getItem() == ModItems.HENDER_TOOL;
        } else if (slot == OUTPUT_SLOT_2) {
            return stack.getItem() == ModItems.HENDER_MACE || stack.getItem() == ModItems.HENDER_TOOL_HELMET || stack.getItem() == ModItems.HENDER_TOOL_CHESTPLATE || stack.getItem() == ModItems.HENDER_TOOL_LEGGINGS || stack.getItem() == ModItems.HENDER_TOOL_BOOTS;
        }
        return false;
    }
}