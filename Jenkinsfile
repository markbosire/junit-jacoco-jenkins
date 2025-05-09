pipeline {
    agent any
    tools {
        // Define Maven tool using the version installed in Jenkins
        maven 'maven'
    }
    
    stages{
        
        stage('Build') {
            steps {
                // Build the project
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                // Run tests with JaCoCo coverage
                sh 'mvn test'
            }
            post {
                always {
                    // Save JUnit test results
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }
    
    post {
        always {
            // Record coverage results with detailed configuration
            // This uses the latest Coverage plugin
            recordCoverage(
                tools: [
                    [parser: 'JACOCO']
                ],
                id: 'jacoco-coverage',
                name: 'JaCoCo Coverage Report',
                // Store source code for every build so we can see coverage history
                sourceCodeRetention: 'EVERY_BUILD',
                // Define quality gates
                qualityGates: [
                    // Line coverage threshold
                    [
                        threshold: 80.0,
                        metric: 'LINE',
                        baseline: 'PROJECT',
                        unstable: true
                    ],
                    // Branch coverage threshold
                    [
                        threshold: 70.0,
                        metric: 'BRANCH',
                        baseline: 'PROJECT',
                        unstable: true
                    ],
                    // Quality gate for modified lines
                    [
                        threshold: 90.0,
                        metric: 'LINE',
                        baseline: 'MODIFIED_LINES',
                        unstable: true
                    ]
                ],
                // Analyze changes between builds
                // This requires Git Forensics plugin to be installed
                skipSymbolicLinks: true,
                sourceDirectories: [
                    [path: 'src/main/java']
                ]
            )
            
            // Also record JUnit test results separately
            recordCoverage(
                tools: [
                    [parser: 'JUNIT']
                ],
                id: 'junit-tests',
                name: 'JUnit Test Results'
            )
        }
    }
}
