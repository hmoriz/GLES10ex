package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Figure1 implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private float x, y, z;

    public Figure1(float s, float x, float y, float z) {
        float t = 0.5f*s;
        float[] vertices = {
                // left
                -t, -t, -t,
                -t, -t, t,
                -t, t, -t,
                -t, t, t,
                // right
                s, -s, -s,
                s, -s, s,
                s, s, -s,
                s, s, s,
                // bottom
                -t, -t, -t,
                s, -s, -s,
                -t, -t, t,
                s, -s, s,
                // top
                -t, t, -t,
                s, s, -s,
                -t, t, t,
                s, s, s,
                // back
                -t, -t, -t,
                -t, t, -t,
                s, -s, -s,
                s, s, -s,
                // front
                -t, -t, t,
                -t, t, t,
                s, -s, s,
                s, s, s
        };
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);

        // left
        gl.glNormal3f(-1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // right
        gl.glNormal3f(1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);

        // top
        gl.glNormal3f(0, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        // rear
        gl.glNormal3f(0, 0, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);

        // front
        gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
    }
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }
}
