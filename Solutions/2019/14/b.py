# March 31st, 2022

file = open("input.txt", 'r')

reaction = {}
for line in file:
	split = line.strip().split(" => ")
	ingredients = split[0].split(", ")
	output = split[1].split(" ")
	obj = {}
	for ingredient in ingredients:
		ingredient = ingredient.split(" ")
		obj[ingredient[1]] = int(ingredient[0])
	reaction[output[1]] = { "amount": int(output[0]), "ingredients": obj }

# Decomposition
materials = { "FUEL": 1 }
nonOre = True
while nonOre:
	nonOre = False
	for x in materials:
		if x != "ORE" and materials[x] > 0:
			nonOre = True
			materials[x] -= reaction[x]["amount"]
			for ingredient in reaction[x]["ingredients"]:
				if ingredient not in materials:
					materials[ingredient] = 0
				materials[ingredient] += reaction[x]["ingredients"][ingredient]
			break

# Setup
out = dict(materials)
for x in materials:
	out[x] *= -1
	materials[x] = 0
materials["ORE"] = 1000000000000
out["FUEL"] = 1

# Convert ORE to FUEL
loop = True
while materials["ORE"] >= -out["ORE"]:
	scale = materials["ORE"] // -out["ORE"]
	for x in materials:
		materials[x] += out[x] * scale

	# Convert remaining chemicals back into ORE
	loop2 = True
	while loop2:
		loop2 = False
		for x in materials:
			if x == "ORE" or x == "FUEL": continue
			value = materials[x] // reaction[x]["amount"]
			if value == 0: continue
			loop2 = True
			materials[x] -= reaction[x]["amount"] * value
			for ingredient in reaction[x]["ingredients"]:
				materials[ingredient] += value * reaction[x]["ingredients"][ingredient]

# FUEL output is correct for examples but not puzzle input.
# It turns out that you can make one more FUEL with the remaining materials.
def make(material):
	if material == "ORE": return False
	retry = True
	while retry:
		retry = False
		for ingredient in reaction[material]["ingredients"]:
			if materials[ingredient] < reaction[material]["ingredients"][ingredient]:
				if not make(ingredient):
					return False
				retry = True
				break
	for ingredient in reaction[material]["ingredients"]:
		materials[ingredient] -= reaction[material]["ingredients"][ingredient]
	materials[material] += reaction[material]["amount"]
	return True

# Brute force remaining ore
while make("FUEL"): pass
print(materials["FUEL"])
