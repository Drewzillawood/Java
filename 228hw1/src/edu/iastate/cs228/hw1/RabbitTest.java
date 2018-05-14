package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RabbitTest
{

	private World	world;
	private World	nWorld;

	@Before
	public void setup()
	{

		world = new World(5);
		nWorld = new World(5);

		for(int i = 0; i < world.getWidth(); i++)
		{

			for(int j = 0; j < world.getWidth(); j++)
			{

				world.grid[i][j] = new Empty(world, i, j);
				nWorld.grid[i][j] = new Empty(nWorld, i, j);

			}

		}

	}

	@Test
	public void testGrandpaRabbit()
	{

		world.grid[0][0] = new Rabbit(world, 0, 0, 3);
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);

		assertEquals(State.EMPTY, nWorld.grid[0][0].who());

	}

	@Test
	public void testStarvingRabbit()
	{

		world.grid[0][0] = new Rabbit(world, 0, 0, 0);
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);

		assertEquals(State.EMPTY, nWorld.grid[0][0].who());

	}

	@Test
	public void testMoreFoxThanBadger()
	{

		world.grid[1][1] = new Rabbit(world, 1, 1, 0);
		world.grid[0][0] = new Fox(world, 0, 0, 0);
		world.grid[0][1] = new Badger(world, 0, 1, 0);
		world.grid[1][0] = new Fox(world, 1, 0, 0);
		world.grid[2][2] = new Grass(world, 2, 2);

		nWorld.grid[1][1] = world.grid[1][1].next(nWorld);

		assertEquals(State.FOX, nWorld.grid[1][1].who());

	}

	@Test
	public void testMoreBadgerThanRabbit()
	{

		world.grid[1][1] = new Rabbit(world, 1, 1, 0);
		world.grid[0][0] = new Fox(world, 0, 0, 0);
		world.grid[0][1] = new Badger(world, 0, 1, 0);
		world.grid[1][0] = new Badger(world, 1, 0, 0);
		world.grid[2][2] = new Grass(world, 2, 2);

		nWorld.grid[1][1] = world.grid[1][1].next(nWorld);

		assertEquals(State.BADGER, nWorld.grid[1][1].who());

	}

	@Test
	public void testRabbitLiveOn()
	{

		world.grid[1][1] = new Rabbit(world, 1, 1, 0);
		world.grid[2][2] = new Grass(world, 2, 2);

		nWorld.grid[1][1] = world.grid[1][1].next(nWorld);

		assertEquals(State.RABBIT, nWorld.grid[1][1].who());
		assertEquals(1, ((Animal)nWorld.grid[1][1]).myAge());

	}

}
