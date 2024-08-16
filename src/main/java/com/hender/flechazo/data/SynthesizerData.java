package com.hender.flechazo.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record SynthesizerData(BlockPos pos) implements BlockPosPayload {
    public static final PacketCodec<RegistryByteBuf, SynthesizerData> CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, SynthesizerData::pos, SynthesizerData::new);
}
