package nl.han.ica.oopd.labyrint.enemies.spells;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Projectile extends AnimatedSpriteObject {

	public Projectile(Labyrint world, Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
	}
	
	@Override
	public void update() {
		fire();		
	}
	
	protected abstract void fire();

}
