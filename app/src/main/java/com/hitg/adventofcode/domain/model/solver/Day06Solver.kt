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

    fun countExternalPath(orbitObject: OrbitObject){
//                G - H       J - K - L
//               /           /
//        COM - B - C - D - E - F
//                       \
//                        I
//        0+1+2+3+2+3+4+4+5+6+7+5
//        com - 0
//        B = 0 + 1 = 1
//        G = 1 + B(1) = 2
//        H = 1 + G(2) = 3
//        C = 1 + B(1) = 2
//        D = 1 + C(2) = 3
//        I = 1 + D(3) = 4
//        E = 1 + D(3) = 4
//        J = 1 + E(4) = 5
//        K = 1 + J(5) = 6
//        L = 1 + K(6) = 7
//        F = 1 + E(4) = 5
    }

    override fun solvePart1(): String? {
        var count = 0;
        orbitsWithoutInternal.forEach {
            val orbitObject = orbitsMap[it]
            orbitObject?.externalObjects?.forEach {

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