package stainless.intermediate

/**
  * Created by koukouto on 10/10/16.
  */
package object oo {

  object trees extends Trees {

    object deconstructor extends {
      protected val s: trees.type = trees
      protected val t: trees.type = trees
    } with TreeDeconstructor

    val NoSymbols = new Symbols(Map.empty, Map.empty, Map.empty)

    class Symbols(
      val functions: Map[Identifier, FunDef],
      val adts: Map[Identifier, ADTDefinition],
      val classes: Map[Identifier, ClassDef]
    ) extends AbstractSymbols {

      def withFunctions(functions: Seq[FunDef]): Symbols = new Symbols(
        this.functions ++ functions.map(fd => fd.id -> fd),
        this.adts,
        this.classes
      )

      def withADTs(adts: Seq[ADTDefinition]): Symbols = new Symbols(
        this.functions,
        this.adts ++ adts.map(adt => adt.id -> adt),
        this.classes
      )

      def withClasses(classes: Seq[ClassDef]): Symbols = new Symbols(
        this.functions,
        this.adts,
        this.classes ++ classes.map(cd => cd.id -> cd)
      )
    }
  }
}
