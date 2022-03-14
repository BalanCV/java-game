package water;

public class WaterUnits {
	
	public static final float TILE_SIZE = 100;
	
	private float height;
	private float x,z;
	
	public WaterUnits(float centerX, float centerZ, float height){
		this.x = centerX;
		this.z = centerZ;
		this.height = height;
	}

	public float getHeight() {
		return height;
	}

	public float getX() {
		return x;
	}

	public float getZ() {
		return z;
	}


}