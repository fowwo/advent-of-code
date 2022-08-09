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

# Remove inert ingredients
ingredients.difference_update(nonallergens)
for r in range(len(recipes)):
	for i in set(recipes[r][0]):
		if i in nonallergens:
			recipes[r][0].remove(i)

# Map allergens to possible ingredients
m = dict()
for a in allergens:
	m[a] = set(ingredients)
	for r in recipes:
		if a in r[1]:
			for i in ingredients:
				if i not in r[0]:
					m[a].discard(i)

# Match allegens to ingredients
b = True
while b:
	b = False
	for a in m:
		if len(m[a]) == 1:
			v = m[a].pop()
			for a2 in m:
				if isinstance(m[a2], set):
					m[a2].discard(v)
			m[a] = v
			b = True

# Canonical dangerous ingredient list
print(','.join([ m[x] for x in sorted(list(m)) ]))
