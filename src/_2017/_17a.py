# February 22nd, 2022

step = 335

i = 0
vortex = [ 0 ]
for x in range(1, 2018):
	i = (i + step) % len(vortex) + 1
	vortex.insert(i, x)
print(vortex[vortex.index(2017) + 1])
