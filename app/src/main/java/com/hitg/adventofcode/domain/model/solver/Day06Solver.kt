package com.hitg.adventofcode.domain.model.solver

import java.util.*

class Day06Solver(input: String) : DaySolver {

    private val orbitsMap: MutableMap<String, OrbitObject> = mutableMapOf()
    private val orbitsWithoutInternal: MutableSet<String> = mutableSetOf()

    init {
        val sc = Scanner(input)
        sc.use {
            while (sc.hasNext()) {
                val objects = sc.next().split(")")
                val internalObject = objects[0]
                val externalObject = objects[1]
                var internalOrbitObject: OrbitObject?
                var externalOrbitObject: OrbitObject?
                if (orbitsMap.containsKey(internalObject)) {
                    internalOrbitObject = orbitsMap[internalObject]
                    if (orbitsMap.containsKey(externalObject)) {
                        externalOrbitObject = orbitsMap[externalObject]
                    } else {
                        externalOrbitObject = OrbitObject()
                        externalOrbitObject.name = externalObject
                    }
                } else {
                    internalOrbitObject = OrbitObject()
                    internalOrbitObject.name = internalObject
                    if (orbitsMap.containsKey(externalObject)) {
                        externalOrbitObject = orbitsMap[externalObject]
                    } else {
                        externalOrbitObject = OrbitObject()
                        externalOrbitObject.name = externalObject
                    }
                }

                if (internalOrbitObject != null && externalOrbitObject != null) {

                    externalOrbitObject.internalObject = internalOrbitObject

                    internalOrbitObject.externalObjects.add(externalOrbitObject)

                    orbitsMap[internalOrbitObject.name] = internalOrbitObject
                    orbitsMap[externalOrbitObject.name] = externalOrbitObject
                    if (internalOrbitObject.internalObject == null) {
                        orbitsWithoutInternal.add(internalOrbitObject.name)
                    } else {
                        orbitsWithoutInternal.remove(internalOrbitObject.name)
                    }
                    if (externalOrbitObject.internalObject != null) {
                        orbitsWithoutInternal.remove(externalOrbitObject.name)
                    }
                }
            }
        }
    }

    var count: Int = 0

    fun countExternalPath(orbitObject: OrbitObject, amount: Int): Int {
        var localCount = 0
        if (orbitObject.internalObject == null) {
            for (externalObject in orbitObject.externalObjects) {
                countExternalPath(externalObject, localCount);
            }
        } else {
            localCount = amount + 1;
            for (externalObject in orbitObject.externalObjects) {
                countExternalPath(externalObject, localCount);
            }
        }
        count += localCount
        return count
    }

    override fun solvePart1(): String? {
        orbitsWithoutInternal.forEach {
            val orbitObject = orbitsMap[it]
            if (orbitObject != null) {
                return countExternalPath(orbitObject, 0).toString()
            }
        }
        return null
    }

    override fun solvePart2(): String? {
        return null
    }
}

class OrbitObject {
    var name = ""
    var internalObject: OrbitObject? = null
    var externalObjects = mutableListOf<OrbitObject>()
}