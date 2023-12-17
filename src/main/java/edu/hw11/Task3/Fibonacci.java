package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

enum Fibonacci implements ByteCodeAppender {

    INSTANCE; // singleton

    @SuppressWarnings("MagicNumber")
    @Override
    public Size apply(MethodVisitor methodVisitor,
        Implementation.Context implementationContext,
        MethodDescription instrumentedMethod) {

        methodVisitor.visitCode();

        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 2);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 4);

        methodVisitor.visitVarInsn(Opcodes.LLOAD, 0);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitInsn(Opcodes.LCMP);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        Label end = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, end);

        Label repeat = new Label();
        methodVisitor.visitLabel(repeat);
        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            3,
            new Object[] {Opcodes.LONG, Opcodes.LONG, Opcodes.LONG},
            0,
            null);


        methodVisitor.visitVarInsn(Opcodes.LLOAD, 2);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 4);
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 2);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 4);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 2);

        methodVisitor.visitVarInsn(Opcodes.LLOAD, 0);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitInsn(Opcodes.LSUB);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 0);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 0);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitInsn(Opcodes.LCMP);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, repeat);

        methodVisitor.visitLabel(end);
        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            3,
            new Object[] {Opcodes.LONG, Opcodes.LONG, Opcodes.LONG },
            0,
            null);

        methodVisitor.visitVarInsn(Opcodes.LLOAD, 4);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitEnd();

        return new Size(4, 6);
    }
}
