# August 8th, 2022

file = open(f"{__file__}/../input.txt", "r")

recipes = []
ingredients = set()
allergens = set()
for line in file:
	line = line.strip()
	i = line.find('(')
	ing = set(line[:i-1].split(' '))
	al = set(line[i+10:-1].split(", "))
	recipes.append((ing, al))
	for x in ing: ingredients.add(x)
	for x in al: allergens.add(x)
file.close()

nonallergens = set()
for i in ingredients:
	s = set()
	for r in recipes:
		if i not in r[0]:
			for a in r[1]:
				s.add(a)
	if s == allergens:
		nonallergens.add(i)

c = 0
for r in recipes:
	for i in r[0]:
		if i in nonallergens:
			c += 1
print(c)
