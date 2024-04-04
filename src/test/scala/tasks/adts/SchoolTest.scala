package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import tasks.adts.SchoolModel.SchoolModuleImpl.*
import u03.Optionals.Optional

class SchoolTest:

    val school: School = tasks.adts.SchoolModel.SchoolModuleImpl.emptySchool()
    val teacher = "Alex"
    val course = "Ingegneria dei sistemi web"


    @Test def initiallyEmptySchool(): Unit =
        assertTrue(Optional.isEmpty(school.courseByName(teacher)))
        assertTrue(Optional.isEmpty(school.teacherByName(course)))
    
    @Test def addTeacher(): Unit = 
        school.addTeacher(teacher)
        val teacherFounded = school.teacherByName(teacher)
        assertEquals(teacher, teacherFounded)