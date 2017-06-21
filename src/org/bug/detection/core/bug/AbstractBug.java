/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import java.util.ArrayList;
import java.util.List;
import org.bug.detection.core.bug.helper.FileHelper;
import org.bug.detection.core.bug.pattern.BugTypeEnum;

/**
 * This class is the base class of this project.
 * Its contains the following parameters:
 * 1. name (String)
 * 2. desc (String)
 * 3. type (BugTypeEnum)
 * 4. line (int)
 * 5. column (int)
 * 6. icon (String)
 * 7. className (String)
 * @author karayel
 */
public abstract class AbstractBug implements Bug {

    private String name;
    private String desc;
    private BugTypeEnum type;
    private int line;
    private int column;
    private String icon;
    private String className;

    private CombinedTypeSolver typeSolver;
    private CompilationUnit cu;

    List<AbstractBug> bdaos;

    public AbstractBug() {
        initilaze();
    }

    public AbstractBug(String source) {
        initilaze();
        String s = FileHelper.readFile(source);
        cu = JavaParser.parse(s);
        setClassName(source);
    }

    private void initilaze() {
        typeSolver = new CombinedTypeSolver();
        typeSolver.add(new ReflectionTypeSolver());
        bdaos = new ArrayList<AbstractBug>();
    }

    public TypeSolver getTypeSolver() {
        return typeSolver;
    }

    public List<AbstractBug> getBdaos() {
        return bdaos;
    }

    public AbstractBug setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getIcon() {
        return icon;
    }
    
    public String getName() {
        return name;
    }

    public AbstractBug setName(String name) {
        this.name = name;
        return this;
    }

    public AbstractBug setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public BugTypeEnum getType() {
        return type;
    }

    public AbstractBug setType(BugTypeEnum type) {
        this.type = type;
        return this;
    }

    public int getLine() {
        return line;
    }

    public AbstractBug setLine(int line) {
        this.line = line;
        return this;
    }

    public int getColumn() {
        return column;
    }

    public AbstractBug setColumn(int column) {
        this.column = column;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public AbstractBug setClassName(String className) {
        this.className = className;
        return this;
    }

    public CompilationUnit getCu() {
        return cu;
    }

    public AbstractBug setCu(CompilationUnit cu) {
        this.cu = cu;
        return this;
    }

    @Override
    public String toString() {
        return getName() + ", " + desc + "," + type + ", " + line + ", " + column + "," + className;
    }
    
    public String customToString(){
        String[] sourcePath = className.split("src");
        String sourceName = sourcePath[sourcePath.length - 1];
        String s = getName() +" - "+ sourceName +" - "+ line +","+ column;
        return s;
    }

}
