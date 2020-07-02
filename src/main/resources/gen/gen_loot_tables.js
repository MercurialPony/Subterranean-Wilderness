"use strict";

const fs = require("fs").promises;

const blocks = [ "smooth_sandstone", "red_sandstone", "smooth_red_sandstone", "terracotta", "white_terracotta", "orange_terracotta", "yellow_terracotta", "light_gray_terracotta", "brown_terracotta", "red_terracota", "ice" ];

async function run()
{
	for(let block of blocks)
	{
		let txt = `{
		"type": "minecraft:block",
		"pools":
		[
			{
				"rolls": 1,
				"entries":
				[
					{
						"type": "minecraft:alternatives",
						"children":
						[
							{
								"type": "minecraft:item",
								"name": "expedition:${block}_coal_ore",
								"conditions":
								[
									{
										"condition": "minecraft:match_tool",
										"predicate":
										{
											"enchantments":
											[
												{
													"enchantment": "minecraft:silk_touch",
													"levels":
													{
														"min": 1
													}
												}
											]
										}
									}
								]
							},
							{
								"type": "minecraft:item",
								"name": "minecraft:coal",
								"functions":
								[
									{
										"function": "minecraft:apply_bonus",
										"enchantment": "minecraft:fortune",
										"formula": "minecraft:ore_drops"
									},
									{
										"function": "minecraft:explosion_decay"
									}
								]
							}
						]
					}
				]
			}
		]
	}`;
		await fs.writeFile(`${block}_coal_ore.json`, txt);

		txt = `{
		"type": "minecraft:block",
		"pools":
		[
			{
				"rolls": 1,
				"entries":
				[
					{
						"type": "minecraft:item",
						"name": "expedition:${block}_iron_ore"
					}
				],
				"conditions":
				[
					{
						"condition": "minecraft:survives_explosion"
					}
				]
			}
		]
	}`;
		await fs.writeFile(`${block}_iron_ore.json`, txt);

		txt = `{
		"type": "minecraft:block",
		"pools":
		[
			{
				"rolls": 1,
				"entries":
				[
					{
						"type": "minecraft:item",
						"name": "expedition:${block}_gold_ore"
					}
				],
				"conditions":
				[
					{
						"condition": "minecraft:survives_explosion"
					}
				]
			}
		]
	}`;
		await fs.writeFile(`${block}_gold_ore.json`, txt);

		txt = `{
		"type": "minecraft:block",
		"pools":
		[
			{
				"rolls": 1,
				"entries":
				[
					{
						"type": "minecraft:alternatives",
						"children":
						[
							{
								"type": "minecraft:item",
								"name": "expedition:${block}_lapis_ore",
								"conditions":
								[
									{
										"condition": "minecraft:match_tool",
										"predicate":
										{
											"enchantments":
											[
												{
													"enchantment": "minecraft:silk_touch",
													"levels":
													{
														"min": 1
													}
												}
											]
										}
									}
								]
							},
							{
								"type": "minecraft:item",
								"name": "minecraft:lapis_lazuli",
								"functions":
								[
									{
										"function": "minecraft:set_count",
										"count":
										{
											"min": 4.0,
											"max": 9.0,
											"type": "minecraft:uniform"
										}
									},
									{
										"function": "minecraft:apply_bonus",
										"enchantment": "minecraft:fortune",
										"formula": "minecraft:ore_drops"
									},
									{
										"function": "minecraft:explosion_decay"
									}
								]
							}
						]
					}
				]
			}
		]
	}`;
		await fs.writeFile(`${block}_lapis_ore.json`, txt);

		txt = `{
		"type": "minecraft:block",
		"pools":
		[
			{
				"rolls": 1,
				"entries":
				[
					{
						"type": "minecraft:alternatives",
						"children":
						[
							{
								"type": "minecraft:item",
								"name": "expedition:${block}_redstone_ore",
								"conditions":
								[
									{
										"condition": "minecraft:match_tool",
										"predicate":
										{
											"enchantments":
											[
												{
													"enchantment": "minecraft:silk_touch",
													"levels":
													{
														"min": 1
													}
												}
											]
										}
									}
								]
							},
							{
								"type": "minecraft:item",
								"name": "minecraft:redstone",
								"functions":
								[
									{
										"function": "minecraft:set_count",
										"count":
										{
											"min": 4.0,
											"max": 5.0,
											"type": "minecraft:uniform"
										}
									},
									{
										"function": "minecraft:apply_bonus",
										"enchantment": "minecraft:fortune",
										"formula": "minecraft:uniform_bonus_count",
										"parameters":
										{
											"bonusMultiplier": 1
										}
									},
									{
										"function": "minecraft:explosion_decay"
									}
								]
							}
						]
					}
				]
			}
		]
	}`;
		await fs.writeFile(`${block}_redstone_ore.json`, txt);

		txt = `{
		"type": "minecraft:block",
		"pools":
		[
			{
				"rolls": 1,
				"entries":
				[
					{
						"type": "minecraft:alternatives",
						"children":
						[
							{
								"type": "minecraft:item",
								"name": "expedition:${block}_diamond_ore",
								"conditions":
								[
									{
										"condition": "minecraft:match_tool",
										"predicate":
										{
											"enchantments":
											[
												{
													"enchantment": "minecraft:silk_touch",
													"levels":
													{
														"min": 1
													}
												}
											]
										}
									}
								]
							},
							{
								"type": "minecraft:item",
								"name": "minecraft:diamond",
								"functions":
								[
									{
										"function": "minecraft:apply_bonus",
										"enchantment": "minecraft:fortune",
										"formula": "minecraft:ore_drops"
									},
									{
										"function": "minecraft:explosion_decay"
									}
								]
							}
						]
					}
				]
			}
		]
	}`;
		await fs.writeFile(`${block}_diamond_ore.json`, txt);

		txt = `{
		"type": "minecraft:block",
		"pools":
		[
			{
				"rolls": 1,
				"entries":
				[
					{
						"type": "minecraft:alternatives",
						"children":
						[
							{
								"type": "minecraft:item",
								"name": "expedition:${block}_emerald_ore",
								"conditions":
								[
									{
										"condition": "minecraft:match_tool",
										"predicate":
										{
											"enchantments":
											[
												{
													"enchantment": "minecraft:silk_touch",
													"levels":
													{
														"min": 1
													}
												}
											]
										}
									}
								]
							},
							{
								"type": "minecraft:item",
								"name": "minecraft:emerald",
								"functions":
								[
									{
										"function": "minecraft:apply_bonus",
										"enchantment": "minecraft:fortune",
										"formula": "minecraft:ore_drops"
									},
									{
										"function": "minecraft:explosion_decay"
									}
								]
							}
						]
					}
				]
			}
		]
	}`;
		await fs.writeFile(`${block}_emerald_ore.json`, txt);
	}
}
run();