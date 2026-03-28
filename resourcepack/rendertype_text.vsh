#version 150

in vec3 Position;
in vec4 Color;
in vec2 UV0;

uniform mat4 ModelViewMat;
uniform mat4 ProjectionMat;
uniform vec4 ColorModulator;

out vec4 vertexColor;
out vec2 texCoord0;

void main() {
    vertexColor = Color * ColorModulator;
    texCoord0 = UV0;
    vec3 pos = Position;

    if (Color.r > 0.99 && Color.r < 0.999 && Color.g > 0.99 && Color.b > 0.99) {
        gl_Position = ProjectionMat * ModelViewMat * vec4(pos.x - 10.0, pos.y + 10.0, pos.z, 1.0);
    } else {
        gl_Position = ProjectionMat * ModelViewMat * vec4(pos, 1.0);
    }
}