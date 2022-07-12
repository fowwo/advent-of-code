# July 11th, 2022

input = 293801

recipes = [ 3, 7 ]
a = 0
b = 1
while len(recipes) < input + 10:
	for recipe in str(recipes[a] + recipes[b]):
		recipes.append(int(recipe))
	a = (a + recipes[a] + 1) % len(recipes)
	b = (b + recipes[b] + 1) % len(recipes)
print("".join(str(x) for x in recipes[input:input+10]))
