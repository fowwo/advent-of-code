# August 4th, 2022

file = open("input.txt", 'r')

class UnitGroup:
	def __init__(this, team, units, health, damageType, damage, weak, immune, initiative):
		this.team = team
		this.units = units
		this.health = health
		this.damageType = damageType
		this.damage = damage
		this.weak = weak
		this.immune = immune
		this.initiative = initiative
	def __str__(this):
		return f"[{this.team}, {this.initiative}] {this.getEffectivePower()} effective power ({this.units} units, {this.damage} {this.damageType} damage), {this.health} health, weak to: {this.weak}, immune to: {this.immune}"
	def getEffectivePower(this): return this.units * this.damage
	def getFutureDamage(this, that):
		damage = this.getEffectivePower()
		if this.damageType in that.immune: return 0
		if this.damageType in that.weak: return damage * 2
		return damage
	def attack(this, that):
		damage = this.getFutureDamage(that)
		that.units -= damage // that.health
		return that.units <= 0

def parseUnitGroup(line, team):
	weak = set()
	immune = set()
	x = line.find('(')
	if x != -1:
		y = line.find(')')
		debuff = line[x+1:y].split("; ")
		line = line[:x] + line[y+1:]
		for d in debuff:
			if d.startswith("weak"): weak = set(d[8:].split(", "))
			else: immune = set(d[10:].split(", "))
	line = line.split()
	return UnitGroup(team, int(line[0]), int(line[4]), line[13], int(line[12]), weak, immune, int(line[17]))

immuneSystem = []
infection = []
file.readline()
line = file.readline().strip()
while line != '':
	immuneSystem.append(parseUnitGroup(line, "immuneSystem"))
	line = file.readline().strip()
file.readline()
line = file.readline().strip()
while line != '':
	infection.append(parseUnitGroup(line, "infection"))
	line = file.readline().strip()
file.close()
groups = immuneSystem + infection

# Simulate
while len(immuneSystem) != 0 and len(infection) != 0:

	# Target selection
	remainingTargets = set(groups)
	targetSelection = dict()
	groups.sort(key=lambda x: (-x.getEffectivePower(), -x.initiative))
	for a in groups:
		targets = infection if a.team == "immuneSystem" else immuneSystem
		targets = [ x for x in targets if x in remainingTargets and a.getFutureDamage(x) != 0 ]
		
		if len(targets) > 1:
			damage = 0
			for enemy in targets:
				damage = max(damage, a.getFutureDamage(enemy))
			targets = [ x for x in targets if a.getFutureDamage(x) == damage ]
		
		if len(targets) > 1:
			effectivePower = 0
			for enemy in targets:
				effectivePower = max(effectivePower, enemy.getEffectivePower())
			targets = [ x for x in targets if x.getEffectivePower() == effectivePower ]
		
		if len(targets) > 1:
			targets = [ max(targets, key=lambda x: x.initiative) ]
		
		if len(targets) == 1:
			targetSelection[a] = targets[0]
			remainingTargets.remove(targets[0])

	# Attacking
	i = 0
	groups.sort(key=lambda x: -x.initiative)
	while i < len(groups):
		a = groups[i]
		b = targetSelection.get(a)
		if b:
			killed = a.attack(b)
			if killed:
				immuneSystem.remove(b) if b.team == "immuneSystem" else infection.remove(b)
				index = groups.index(b)
				groups.pop(index)
				if index < i:
					i -= 1
		i += 1

s = 0
winner = immuneSystem if len(immuneSystem) > 0 else infection
for x in winner: s += x.units
print(s)
