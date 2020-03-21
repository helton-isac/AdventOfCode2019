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

    private var count: Int = 0

    private fun countExternalPath(orbitObject: OrbitObject, amount: Int): Int {
        var localCount = 0
        if (orbitObject.internalObject == null) {
            for (externalObject in orbitObject.externalObjects) {
                countExternalPath(externalObject, localCount)
            }
        } else {
            localCount = amount + 1
            for (externalObject in orbitObject.externalObjects) {
                countExternalPath(externalObject, localCount)
            }
        }
        count += localCount
        return count
    }

    override fun solvePart1(): String? {
        count = 0
        orbitsWithoutInternal.forEach {
            val orbitObject = orbitsMap[it]
            if (orbitObject != null) {
                return countExternalPath(orbitObject, 0).toString()
            }
        }
        return null
    }

    override fun solvePart2(): String? {
        val objectYou = orbitsMap["YOU"]
        if (objectYou != null) {
            return findSanta(objectYou.internalObject, 0, objectYou.name).toString()
        }
        return count.toString()
    }

    private fun findSanta(
        orbitObject: OrbitObject?,
        steps: Int,
        callerName: String
    ): Int {
        if (orbitObject != null) {
            return if (orbitObject.name == "SAN") {
                steps - 1
            } else {
                if (orbitObject.internalObject != null && orbitObject.internalObject!!.name != callerName) {
                    val stepsToSanta =
                        findSanta(orbitObject.internalObject, steps + 1, orbitObject.name)
                    if (stepsToSanta != 0) {
                        stepsToSanta
                    } else {
                        findSantaExternal(orbitObject, callerName, steps)
                    }
                } else {
                    findSantaExternal(orbitObject, callerName, steps)
                }
            }
        }
        return 0
    }

    private fun findSantaExternal(
        orbitObject: OrbitObject,
        callerName: String,
        steps: Int
    ): Int {
        var stepsToSanta: Int
        for (externalObject in orbitObject.externalObjects) {
            if (externalObject.name != callerName) {
                stepsToSanta = findSanta(
                    externalObject,
                    steps + 1,
                    orbitObject.name
                )
                if (stepsToSanta != 0) {
                    return stepsToSanta
                }
            }
        }
        return 0
    }
}

class OrbitObject {
    var name = ""
    var internalObject: OrbitObject? = null
    var externalObjects = mutableListOf<OrbitObject>()
}