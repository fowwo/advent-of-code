# March 29th, 2022

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
print(materials["ORE"])
