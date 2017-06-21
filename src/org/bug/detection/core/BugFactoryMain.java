/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core;

import java.util.List;
import org.bug.detection.core.bug.AbstractBug;

/**
 *
 * @author karayel
 */
public interface BugFactoryMain {
    public List<AbstractBug> findBugs(String source);
}
