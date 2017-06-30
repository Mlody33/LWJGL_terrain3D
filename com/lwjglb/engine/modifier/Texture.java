package com.lwjglb.engine.modifier;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import java.io.InputStream;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {

    private final int id;
    private final int width;
    private final int height;

    public Texture(String fileName) throws Exception {
        this(Texture.class.getResourceAsStream(fileName));
    }

    public Texture(InputStream is) throws Exception {
        PNGDecoder decoder = new PNGDecoder(is);

        this.width = decoder.getWidth();
        this.height = decoder.getHeight();

        ByteBuffer buf = ByteBuffer.allocateDirect(decoder.getWidth() * decoder.getHeight());
        decoder.decode(buf, decoder.getWidth(), Format.RGBA);
        buf.flip();

        this.id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, this.id);

        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getId() {
        return id;
    }

    public void cleanup() {
        glDeleteTextures(id);
    }
}
