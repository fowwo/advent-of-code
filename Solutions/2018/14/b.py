# July 11th, 2022

input = 293801

recipes = [ 3, 7 ]
a = 0
b = 1
while True:
	for recipe in str(recipes[a] + recipes[b]):
		recipes.append(int(recipe))
	a = (a + recipes[a] + 1) % len(recipes)
	b = (b + recipes[b] + 1) % len(recipes)
	if f"{input}" == "".join(str(x) for x in recipes[-6:]):
		print(len(recipes) - 6)
		exit(0)
	if f"{input}0" == "".join(str(x) for x in recipes[-7:]):
		print(len(recipes) - 7)
		exit(0)
