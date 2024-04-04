package tasks.adts
import u03.Sequences.*
import u03.Optionals.*
import u02.AlgebraicDataTypes.Person

/*  Exercise 2:
 *  Implement the below trait, and write a meaningful test.
 *  Suggestion:
 *  - reuse Sequences and Optionals as imported above
 *  - Course is a simple case classes with just the name
 *  - Teacher is a case class with name and sequence of courses
 *  - School is a case class with (sequences of) teachers and courses
 *  - add/set methods below create the new school
 */

object SchoolModel:

  trait SchoolModule:
    type School
    type Teacher
    type Course
    extension (school: School)
      def addTeacher(name: String): School
      def addCourse(name: String): School
      def teacherByName(name: String): Optional[Teacher]
      def courseByName(name: String): Optional[Course]
      def nameOfTeacher(teacher: Teacher): String
      def nameOfCourse(teacher: Course): String
      def setTeacherToCourse(teacher: Teacher, course: Course): School
      def coursesOfATeacher(teacher: Teacher): Sequence[Course]

  object SchoolModuleImpl extends SchoolModule:
    import u03.Sequences.Sequence.*

    private case class SchoolImpl(
        teachers: Sequence[Teacher],
        courses: Sequence[Course]
    )
    private case class TeacherImpl(name: String, courses: Sequence[Course])
    private case class CourseImpl(name: String)

    opaque type School = SchoolImpl
    opaque type Teacher = TeacherImpl
    opaque type Course = CourseImpl

    def emptySchool(): School = SchoolImpl(Nil(), Nil())

    extension (school: School)
      def nameOfCourse(teacher: Course): String = ???
      def coursesOfATeacher(teacher: Teacher): Sequence[Course] = ???
      def courseByName(name: String): Optional[Course] = ???
      def addCourse(name: String): School = school match
        case SchoolImpl(t, c) => SchoolImpl(t, Cons(CourseImpl(name), c))
      
      def nameOfTeacher(teacher: Teacher): String = ???
      def addTeacher(name: String): School = school match
        case SchoolImpl(t, c) => SchoolImpl(Cons(TeacherImpl(name, Nil()), t), c)
      
      def setTeacherToCourse(teacher: Teacher, course: Course): School = ???
      def teacherByName(name: String): Optional[Teacher] = 
        filter(school.teachers)((n) => n.name == name) match
          case Cons(h, t) => Optional.Just(h)
          case _ => Optional.Empty()
        

